package top.liwanyu.project_server.model.query;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class UserQuery extends BaseQuery{
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
    private  String addrCode;
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
}
