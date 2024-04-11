/*
 * @Description: 
 * @Version: 
 * @Author: liwanyu
 * @Date: 2024-04-10 09:51:50
 * @LastEditors: liwanyu
 * @LastEditTime: 2024-04-10 09:52:14
 */
package top.liwanyu.project_server.utils;

import java.security.MessageDigest;
import java.util.Base64;
import java.util.UUID;

public class Md5Utils {


    /**
     * 创建盐
     * @return
     */
    public static String createSalt(){
       return UUID.randomUUID().toString();
    }

    /**
     * 密码加密
     * @param password
     * @param salt
     * @return
     */
    public static String md5Password(String password,String salt){

        try {
            //获取md5算法对象
            MessageDigest messageDigest = MessageDigest.getInstance("md5");

            byte[] digest = messageDigest.digest((password + salt).getBytes());

            //base64进行编码
            Base64.Encoder encoder = Base64.getEncoder();
            byte[] encode = encoder.encode(digest);
            return new String(encode);

        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException("加密失败!");
        }

    }
}
