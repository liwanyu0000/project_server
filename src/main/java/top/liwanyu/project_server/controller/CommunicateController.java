package top.liwanyu.project_server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import top.liwanyu.project_server.model.param.CommunicateParam;
import top.liwanyu.project_server.service.intf.CommunicateIntf;
import top.liwanyu.project_server.utils.TokenUtils;

@RestController
@RequestMapping("/Communicate")
public class CommunicateController {
    
    @Autowired
    private CommunicateIntf communicateIntf;

    @Resource
    private TokenUtils tokenUtils;
    
    @RequestMapping("/createCommunicate")
    public Boolean createCommunicate(@RequestBody CommunicateParam communicateParam, HttpServletRequest request) {
        Integer fromId =  tokenUtils.getId(request.getHeader("token"));
        return communicateIntf.createCommunicate(fromId, communicateParam);

    }
    
    @RequestMapping("/addCommunicate")
    public Boolean addCommunicate(@RequestBody CommunicateParam communicateParam, HttpServletRequest request){
        Integer fromId = tokenUtils.getId(request.getHeader("token"));
        return communicateIntf.addCommunicate(fromId, communicateParam);
    }
    
    @RequestMapping("/getCommunicate")
    public String getCommunicate(@RequestBody CommunicateParam communicateParam, HttpServletRequest request) {
        
        Integer fromId = tokenUtils.getId(request.getHeader("token"));
        return communicateIntf.getCommunicate(fromId, communicateParam);
    }
    
    
    
}
