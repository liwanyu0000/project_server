package top.liwanyu.project_server.model.param;

import lombok.Data;

@Data
public class BaseParam {
    private String q;
    private String code;
    private String codeToken;
    private Integer pageSize;
    private Integer pageNum;
}
