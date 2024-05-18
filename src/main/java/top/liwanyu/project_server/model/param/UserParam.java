package top.liwanyu.project_server.model.param;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class UserParam extends BaseParam {
    // id
    private Integer id;
    // 用户名
    private String userName;
    // 旧密码
    private String oldPassword;
    // 密码
    private String password;
    // 确认密码
    private String secondPassword;
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
    // 详细地址
    private String addr;
    // 权限
    private String userPermission;
    // 额外的信息
    private String extraInfo;

}
