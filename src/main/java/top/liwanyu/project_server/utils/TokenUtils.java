/*
 * @Description: 
 * @Version: 
 * @Author: liwanyu
 * @Date: 2024-04-10 09:46:16
 * @LastEditors: liwanyu
 * @LastEditTime: 2024-04-10 20:42:00
 */
package top.liwanyu.project_server.utils;

import top.liwanyu.project_server.aspect.GlobalException;
import top.liwanyu.project_server.constant.enums.ResultStatus;
import top.liwanyu.project_server.mapper.UserMapper;
import top.liwanyu.project_server.model.entity.UserEntity;

import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import io.netty.util.internal.StringUtil;

@Component
public class TokenUtils {

    @Autowired
    UserMapper userMapper;

    @Autowired
    RedisTemplate<String, Object> redisTemplate;

    private static final long EXPIRE_DATE = 5;

    public String createToken(int userId) {
        String token = UUID.randomUUID().toString();
        redisTemplate.opsForValue().set("TOKEN_" + token, userId, EXPIRE_DATE,
                TimeUnit.DAYS);
        return token;
    }

    /**
     * 验证token, 获取信息
     *
     * @param token
     * @return
     */
    public int getId(String token) {
        if (StringUtil.isNullOrEmpty(token)) {
            throw new GlobalException(ResultStatus.NOT_EXISTS_TOKEN);
        }
        String key = "TOKEN_" + token;
        Object objs = redisTemplate.opsForValue().get(key);

        if (objs == null) {
            throw new GlobalException(ResultStatus.INVALID_TOKEN);
        }
        try {
            return (int) objs;
        } catch (Exception e) {
            redisTemplate.delete(key);
            throw new GlobalException(ResultStatus.INVALID_TOKEN);
        }
    }

    public int verifyAdmin(String token) {
        if (StringUtil.isNullOrEmpty(token)) {
            throw new GlobalException(ResultStatus.NOT_EXISTS_TOKEN);
        }
        String key = "TOKEN_" + token;
        Object objs = redisTemplate.opsForValue().get(key);
        if (objs == null) {
            throw new GlobalException(ResultStatus.INVALID_TOKEN);
        }
        UserEntity userEntity;
        try {
             userEntity = userMapper.findUserById(Objects.requireNonNull((int) objs));
            

        } catch (Exception e) {
            redisTemplate.delete(key);
            throw new GlobalException(ResultStatus.INVALID_TOKEN);
        }
        if (!userEntity.isAdministrator()) {
            throw new GlobalException(ResultStatus.NOT_IS_ADMIN);
        }
        return userEntity.getId();
    }

    public void deleteToken(String token) {
        if (StringUtil.isNullOrEmpty(token)) {
            throw new GlobalException(ResultStatus.NOT_EXISTS_TOKEN);
        }
        String key = "TOKEN_" + token;
        redisTemplate.delete(key);
    }

}