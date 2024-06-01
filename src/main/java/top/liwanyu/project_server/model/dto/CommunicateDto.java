package top.liwanyu.project_server.model.dto;

import lombok.Data;

@Data
public class CommunicateDto {
    // id
    private Integer id;
    // 用户id
    private BaseUserDto userModelOne;
    // 用户id
    private BaseUserDto userModelTwo;
    // 信息
    private String content;
    // 创建时间
    private String createTime;
}
