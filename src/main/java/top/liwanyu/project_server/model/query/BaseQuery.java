package top.liwanyu.project_server.model.query;

import lombok.Data;

/**
 * 基础查询类
 */
@Data
public class BaseQuery {
    private String q;
    private Integer pageSize;
    private Integer offset;
    // id
    private Integer id;
}
