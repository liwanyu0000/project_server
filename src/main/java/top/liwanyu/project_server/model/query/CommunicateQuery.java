package top.liwanyu.project_server.model.query;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class CommunicateQuery extends BaseQuery {
    private  Integer id;
    // 用户id
    private Integer userIdOne;
    // 用户id
    private Integer userIdTwo;
    // 内容
    private String content;
    // 创建时间
    private String createTime;
}
