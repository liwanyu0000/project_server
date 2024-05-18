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
import top.liwanyu.project_server.constant.consist.User;

@Data
public class UserEntity {
    // id
    private int id;
    // 用户名
    private String userName;
    // 密码
    private String password;
    // 昵称
    private String nickName;
    // 手机号
    private String phones;
    // 邮箱
    private String emails;
    // 头像
    private String avatar;
    // 地址码
    private String addrCode;
    // 地址
    private String addr;
    // 创建时间
    private String createTime;
    // 更新时间
    private String updateTime;
    // 权限
    private String userPermission;
    // 盐
    private String pwsalt;
    // 额外的信息
    private String extraInfo;

    public String getUserPermission(){
        if (userPermission == null) {
            return "";
        }
        return userPermission;
    }

    public boolean isAdministrator() {
        return getUserPermission().contains(User.USER_PERMISSION_ADMIN);
    }

    public boolean canLogin() {
        return getUserPermission().contains(User.USER_PERMISSION_LOGIN);
    }

    public boolean canPublish() {
        return getUserPermission().contains(User.USER_PERMISSION_PUBLISH);
    }
}
