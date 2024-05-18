package top.liwanyu.project_server.model.entity;

import lombok.Data;

@Data
public class CommunicateEntity {
    // id
    private Integer id;
    // 用户id
    private Integer userIdOne;
    // 用户id
    private Integer userIdTwo;
    // 信息
    private String content;
    // 创建时间
    private String createTime;
    // 剩余消息条数
    private Integer messageNum;
    
}
