/*
 * @Description: 
 * @Version: 
 * @Author: liwanyu
 * @Date: 2024-04-10 10:07:11
 * @LastEditors: liwanyu
 * @LastEditTime: 2024-04-10 10:09:22
 */
package top.liwanyu.project_server.model.dto;

import lombok.Data;

@Data
public class UserDto {
    // id
    private int id;
    // 用户名
    private String userName;
    // 昵称
    private String nickName;
    // 手机号
    private String phone;
    // 邮箱
    private String email;
    // 头像
    private String avatar;
    // 状态
    private String addr;
    // token
    private String token;
    // 创建时间
    private String createTime;
    // 更新时间
    private String updateTime;
    // 角色
    private String userRole;
    // 权限
    private String userPermission;  
}
