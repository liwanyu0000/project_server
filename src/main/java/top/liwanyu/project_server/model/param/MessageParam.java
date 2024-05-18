package top.liwanyu.project_server.model.param;

import lombok.Data;

@Data
public class MessageParam {
    private String message;
    private String type;
    private Integer to;
}
