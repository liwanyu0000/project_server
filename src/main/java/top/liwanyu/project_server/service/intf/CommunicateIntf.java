package top.liwanyu.project_server.service.intf;

import java.util.List;

import top.liwanyu.project_server.model.dto.BaseUserDto;
import top.liwanyu.project_server.model.dto.CommunicateDto;
import top.liwanyu.project_server.model.param.CommunicateParam;

public interface CommunicateIntf {
    // public Integer createCommunicate(Integer userIdOne, CommunicateParam communicateParam);

    public boolean addCommunicate(Integer userIdOne, CommunicateParam communicateParam);

    public CommunicateDto getCommunicate(Integer userIdOne, CommunicateParam communicateParam);

    public List<BaseUserDto> getCommunicateList(Integer userId);
    
}
