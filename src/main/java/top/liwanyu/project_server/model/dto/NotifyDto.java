package top.liwanyu.project_server.model.dto;

import java.util.List;

import lombok.Data;

@Data
public class NotifyDto {
    public NotifyDto() {
    }

    public NotifyDto(String channel) {
        this.channel = channel;
        this.host = "liwanyu.top";
        this.port = 6379;
        this.pwd = "project_server";
    }

    // 地址
    private String host;
    // 端口
    private Integer port;
    // 通道
    private String channel;
    // 密码
    private String pwd;
    // data
    private List<MessageDto> data;
}
