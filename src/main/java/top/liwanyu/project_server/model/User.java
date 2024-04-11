/*
 * @Description: 
 * @Version: 
 * @Author: liwanyu
 * @Date: 2024-04-10 10:07:11
 * @LastEditors: liwanyu
 * @LastEditTime: 2024-04-10 10:09:22
 */
package top.liwanyu.project_server.model;

import lombok.Data;

@Data
public class User {
    private int id;
    private String userName;
    private String passWord;
    private String nickName;
    private String phone;
    private String email;
    private String avatar;
    private String addr;
}
