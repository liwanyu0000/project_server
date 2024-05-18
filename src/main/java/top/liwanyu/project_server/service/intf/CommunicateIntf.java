package top.liwanyu.project_server.service.intf;

import top.liwanyu.project_server.model.param.CommunicateParam;

public interface CommunicateIntf {
    public boolean createCommunicate(Integer userIdOne, CommunicateParam communicateParam);

    public boolean addCommunicate(Integer userIdOne, CommunicateParam communicateParam);

    public String getCommunicate(Integer userIdOne, CommunicateParam communicateParam);
    
}
