package top.liwanyu.project_server.mapper;

import top.liwanyu.project_server.model.entity.CommunicateEntity;
import top.liwanyu.project_server.model.query.CommunicateQuery;

public interface CommunicateMapper {
    public Integer creatCommunicate(CommunicateQuery communicateQuery);
    
    public Integer addCommunicate(CommunicateQuery communicateQuery);

    public CommunicateEntity getCommunicate(CommunicateQuery communicateQuery);

    public CommunicateEntity getCanAddCommunicate(CommunicateQuery communicateQuery);
    
}
