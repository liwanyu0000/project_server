/*
 * @Description: 
 * @Version: 
 * @Author: liwanyu
 * @Date: 2024-04-10 09:46:16
 * @LastEditors: liwanyu
 * @LastEditTime: 2024-04-10 20:42:00
 */
package top.liwanyu.project_server.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;

import top.liwanyu.project_server.exception.GlobalException;
import top.liwanyu.project_server.model.User;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * jwt工具类
 */
public class JwtUtils {

    private static final long EXPIRE_DATE = 30 * 60 * 1000;
    //token秘钥
    private static final String TOKEN_SECRET = "liwanyu@jwt_token%%XXEECC";

    /**
     * 生成token
     *
     * @param username
     * @param userId
     * @return
     */
    public static String token(User user) {

        String token = "";
        try {
            //过期时间
            Date date = new Date(System.currentTimeMillis() + EXPIRE_DATE);
            //秘钥及加密算法
            Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
            //设置头部信息
            Map<String, Object> header = new HashMap<>();
            header.put("typ", "JWT");
            header.put("alg", "HS256");
            //携带USER信息，生成签名
            token = JWT.create()
                    .withHeader(header)
                    .withClaim("id", user.getId())
                    .withClaim("userName", user.getUserName())
                    .withClaim("passWord", user.getPassWord())
                    .withClaim("nickName", user.getNickName())
                    .withClaim("phone", user.getPhone())
                    .withClaim("email", user.getEmail())
                    .withClaim("avatar", user.getAvatar())
                    .withClaim("addr", user.getAddr())
                    .withExpiresAt(date)
                    .sign(algorithm);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return token;
    }

    /**
     * 校验
     *
     * @param token
     * @return
     */
    public static boolean verify(String token) {
        /**
         * @desc 验证token，通过返回true
         * @params [token]需要校验的串
         **/
        try {
            Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
            JWTVerifier verifier = JWT.require(algorithm).build();
            verifier.verify(token);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 判断token是否需要进行续期
     */
    public static boolean renewal(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
            JWTVerifier verifier = JWT.require(algorithm).build();
            DecodedJWT jwt = verifier.verify(token);
            Date expiresAt = jwt.getExpiresAt();
            //计算还剩多少时间过期
            long diffInMillies = expiresAt.getTime() - System.currentTimeMillis();
            long diffInMinutes = TimeUnit.MILLISECONDS.toMinutes(diffInMillies);

            return diffInMinutes<10;
        } catch (Exception e) {
            e.printStackTrace();
            throw new GlobalException(MessageCode.INVALID_TOKEN);
        }

    }


    public static User parse(String token) {
        DecodedJWT decodedJWT = JWT.decode(token);

        Map<String, Claim> claims = decodedJWT.getClaims();
       

        User user = new User();
        user.setId(claims.get("id").asInt());
        user.setUserName(claims.get("userName").asString());
        user.setPassWord(claims.get("passWord").asString());
        user.setNickName(claims.get("nickName").asString());
        user.setPhone(claims.get("phone").asString());
        user.setEmail(claims.get("email").asString());
        user.setAvatar(claims.get("avatar").asString());
        user.setAddr(claims.get("addr").asString());

        return user;

    }
}