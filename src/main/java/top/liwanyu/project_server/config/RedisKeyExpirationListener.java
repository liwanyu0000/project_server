package top.liwanyu.project_server.config;

import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.KeyExpirationEventMessageListener;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.stereotype.Component;

import jakarta.annotation.Resource;
import top.liwanyu.project_server.constant.consist.NotifyConfig;


@Component
public class RedisKeyExpirationListener extends KeyExpirationEventMessageListener {

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * 创建RedisKeyExpirationListener bean时注入 redisMessageListenerContainer
     *
     * @param redisMessageListenerContainer RedisConfig中配置的消息监听者容器bean
     */
    public RedisKeyExpirationListener(RedisMessageListenerContainer redisMessageListenerContainer) {
        super(redisMessageListenerContainer);
    }

    @SuppressWarnings("null")
    @Override
    public void onMessage(Message message, byte[] pattern) {
        String expiredKey = message.toString();
        System.out.println("监听到过期key：" + expiredKey);
        if (expiredKey.startsWith(NotifyConfig.CHANNELPREFIX)) {
            // 删除过期的通道
            redisTemplate.opsForSet().remove(NotifyConfig.CHANNELSET, expiredKey);
        }
    }
}