package top.liwanyu.project_server.service.impl;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.util.Base64;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Component;

import top.liwanyu.project_server.aspect.GlobalException;
import top.liwanyu.project_server.constant.enums.ResultStatus;
import top.liwanyu.project_server.model.dto.VerifyDto;
import top.liwanyu.project_server.service.intf.VerifyIntf;
import top.liwanyu.project_server.utils.VerifyUtil;

@Component
public class VerifyImpl implements VerifyIntf {

    @Autowired
    RedisTemplate<String, String> redisTemplate;

    @Override
    public VerifyDto getVerifyCode() {
        // get session id as key
        String token = UUID.randomUUID().toString();
        // generate image
        Object[] objs = VerifyUtil.newBuilder()
                .setWidth(80)
                .setHeight(40)
                .setSize(4)
                .setLines(5)
                .setFontSize(30)
                .setTilt(true)
                .setBackgroundColor(Color.LIGHT_GRAY)
                .build()
                .createImage();
        // print verify code
        System.out.println(objs[0]);

        // convert image to String
        String code;
        try {
            BufferedImage image = (BufferedImage) objs[1];
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(image, "png", baos);
            code = Base64.getEncoder().encodeToString(baos.toByteArray());
        } catch (Exception e) {
            throw new RuntimeException("Failed to convert image to string");
        }
        // store verify code in redis
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.opsForValue().set(("VERIFY_CODE_" + token), objs[0].toString(), 60 * 5, TimeUnit.SECONDS);

        VerifyDto verifyDto = new VerifyDto();
        verifyDto.setCodeToken(token);
        verifyDto.setCodeImg(code);

        return verifyDto;
    }

    @Override
    public void checkVerifyCode(String code, String codeToken) {
        if (code == null || code.isEmpty())
            throw new GlobalException(ResultStatus.VERIFY_CODE_NOT_NULL);
        String verifyCode = "VERIFY_CODE_" + codeToken;
        String serverCode = redisTemplate.opsForValue().get(verifyCode);
        if (serverCode == null || serverCode.isEmpty()) {
            redisTemplate.delete(verifyCode);
            throw new GlobalException(ResultStatus.VERIFY_CODE_EXPIRED);
        }
        if (!code.equalsIgnoreCase(serverCode))
            throw new GlobalException(ResultStatus.VERIFY_CODE_ERROR);
        redisTemplate.delete(verifyCode);
    }

}
