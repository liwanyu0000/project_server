package top.liwanyu.project_server.model.dto;

import lombok.Data;

@Data
public class MessageDto<T> {
    static public final String MESSAGE_TYPE = "MESSAGE";
    static public final String NOTICE_HOUSE_TYPE = "NOTICE_HOUSE";
    static public final String NOTICE_USER_TYPE = "NOTICE_USER";
    private T data;
    private String type;
    private BaseUserDto from;
    private Integer to;
    private String time;
}
