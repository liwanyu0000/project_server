package top.liwanyu.project_server.model.dto;

import lombok.Data;

@Data
public class BaseUserDto {
    // id
    private Integer id;
    // 用户昵称
    private String nickName;
    // 用户头像
    private String avatar;
    // 地址码
    private String addrCode;
    // 用户住址
    private String addr;
}
