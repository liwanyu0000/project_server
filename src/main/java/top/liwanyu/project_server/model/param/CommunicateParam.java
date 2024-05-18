package top.liwanyu.project_server.model.param;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class CommunicateParam extends BaseParam {
    private Integer userIdTwo;
    private String content;
    private String creatTime;

}
