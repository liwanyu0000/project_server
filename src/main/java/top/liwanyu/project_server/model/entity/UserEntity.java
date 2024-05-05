/*
 * @Description: 
 * @Version: 
 * @Author: liwanyu
 * @Date: 2024-04-10 10:07:11
 * @LastEditors: liwanyu
 * @LastEditTime: 2024-04-10 10:09:22
 */
package top.liwanyu.project_server.model.entity;

import lombok.Data;

@Data
public class UserEntity {
    // id
    private int id;
    // 用户名
    private String userName;
    // 密码
    private String passWord;
    // 昵称
    private String nickName;
    // 手机号
    private String phone;
    // 邮箱
    private String email;
    // 头像
    private String avatar;
    // 地址
    private String addr;
    // 创建时间
    private String createTime;
    // 更新时间
    private String updateTime;
    // 角色
    private String userRole;
    // 权限
    private String userPermission;  
    // 盐
    private String pwsalt;
}
