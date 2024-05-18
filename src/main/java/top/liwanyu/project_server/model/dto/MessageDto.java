package top.liwanyu.project_server.model.dto;

import lombok.Data;

@Data
public class MessageDto {
    static public final String MESSAGETYPE = "MESSAGE";
    private String message;
    private String type;
    private BaseUserDto from;
    private Integer to;
    private String time;
}
