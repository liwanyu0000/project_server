package top.liwanyu.project_server.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import jakarta.annotation.Resource;
import top.liwanyu.project_server.constant.consist.NotifyConfig;
import top.liwanyu.project_server.mapper.UserMapper;
import top.liwanyu.project_server.model.dto.BaseUserDto;
import top.liwanyu.project_server.model.dto.MessageDto;
import top.liwanyu.project_server.model.dto.NotifyDto;
import top.liwanyu.project_server.model.dto.HouseDto;
import top.liwanyu.project_server.model.dto.UserDto;
import top.liwanyu.project_server.model.entity.UserEntity;
import top.liwanyu.project_server.service.intf.NotifyIntf;
import top.liwanyu.project_server.utils.BeanCopyUtils;
import top.liwanyu.project_server.utils.DateUtils;

@Component
public class NotifyImpl implements NotifyIntf {
    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @Resource
    private UserMapper userMapper;

    @Override
    public NotifyDto createChannel(Integer id) {
        // 拿取缓存的消息
        Set<Object> messages = redisTemplate.opsForSet().members(NotifyConfig.MESSAGEPREFIX + id);
        if (messages == null) {
            messages = new HashSet<>();
        }
        @SuppressWarnings("rawtypes")
        List<MessageDto> messageDtos = messages.stream()
                .map(message -> BeanCopyUtils.copyBean(message, MessageDto.class)).toList();
        int num = (new Random()).nextInt(9999);
        String channel = String.format(NotifyConfig.LOGINCHANNEL, num, id);
        // 设置过期时间
        redisTemplate.opsForValue().set(channel, num, NotifyConfig.CHANNELTIMEOUT, TimeUnit.MILLISECONDS);
        // 添加到通道集合
        redisTemplate.opsForSet().add(NotifyConfig.CHANNELSET, channel);
        NotifyDto notifyDto = new NotifyDto(channel);
        notifyDto.setData(messageDtos);
        redisTemplate.delete(NotifyConfig.MESSAGEPREFIX + id);
        return notifyDto;
    }

    @Override
    public NotifyDto createChannel() {
        int num = (new Random()).nextInt(9999);
        String channel = String.format(NotifyConfig.NORMALCHANNEL, num);
        redisTemplate.opsForValue().set(channel, num, NotifyConfig.CHANNELTIMEOUT, TimeUnit.MILLISECONDS);
        redisTemplate.opsForSet().add(NotifyConfig.CHANNELSET, channel);
        System.out.println(channel + ": " + redisTemplate.opsForValue().get(channel));
        ;
        return new NotifyDto(channel);
    }

    @Override
    public Boolean sendNotify(String data, Integer to, Integer from) {
        UserEntity userEntity = userMapper.findUserById(from);
        MessageDto<String> messageDto = new MessageDto<String>();
        messageDto.setType(MessageDto.MESSAGE_TYPE);
        messageDto.setFrom(BeanCopyUtils.copyBean(userEntity, BaseUserDto.class));
        messageDto.setTime(DateUtils.getNowDate());
        messageDto.setTo(to);
        messageDto.setData(data);
        Set<Object> channels = redisTemplate.opsForSet().members(NotifyConfig.CHANNELSET);
        boolean findUser = false;
        if (channels != null) {
            for (Object channel : channels) {
                Integer id = NotifyConfig.getId(channel.toString());
                if (id != null && id.equals(to)) {
                    redisTemplate.convertAndSend(channel.toString(), messageDto);
                    findUser = true;
                }
            }
        }
        if (!findUser) {
            redisTemplate.opsForSet().add(NotifyConfig.MESSAGEPREFIX + to, messageDto);
        }
        return true;
    }

    @Override
    public Boolean sendNotify(UserDto userDto) {
        Set<Object> channels = redisTemplate.opsForSet().members(NotifyConfig.CHANNELSET);
        if (channels == null)
            return true;
        MessageDto<UserDto> message = new MessageDto<UserDto>();
        message.setTime(DateUtils.getNowDate());
        message.setData(userDto);
        message.setType(MessageDto.NOTICE_USER_TYPE);
        for (Object channel : channels) {
            redisTemplate.convertAndSend(channel.toString(), message);
        }
        return true;
    }

    @Override
    public Boolean sendNotify(HouseDto houseDto) {
        Set<Object> channels = redisTemplate.opsForSet().members(NotifyConfig.CHANNELSET);
        if (channels == null)
            return true;
        MessageDto<HouseDto> message = new MessageDto<HouseDto>();
        message.setTime(DateUtils.getNowDate());
        message.setData(houseDto);
        message.setType(MessageDto.NOTICE_HOUSE_TYPE);
        for (Object channel : channels) {
            redisTemplate.convertAndSend(channel.toString(), message);
        }
        return true;
    }

    @Override
    public Boolean keepHeartbeat(String channel) {
        System.out.println(channel + ": " + redisTemplate.opsForValue().get(channel));
        return redisTemplate.expire(channel, NotifyConfig.CHANNELTIMEOUT, TimeUnit.MILLISECONDS);
    }

    @Override
    public Boolean deleteChannel(String channel) {
        redisTemplate.opsForSet().remove(NotifyConfig.CHANNELSET, channel);
        redisTemplate.delete(channel);
        return true;
    }

}
