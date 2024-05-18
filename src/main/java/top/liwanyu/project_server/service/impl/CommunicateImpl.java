package top.liwanyu.project_server.service.impl;

import org.springframework.stereotype.Component;

import top.liwanyu.project_server.model.param.CommunicateParam;
import top.liwanyu.project_server.service.intf.CommunicateIntf;

@Component
public class CommunicateImpl implements CommunicateIntf {

    @Override
    public boolean createCommunicate(Integer userIdOne, CommunicateParam communicateParam) {
        throw new UnsupportedOperationException("Unimplemented method 'createCommunicate'");
    }

    @Override
    public boolean addCommunicate(Integer userIdOne, CommunicateParam communicateParam) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addCommunicate'");
    }

    @Override
    public String getCommunicate(Integer userIdOne, CommunicateParam communicateParam) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getCommunicate'");
    }
    
}
