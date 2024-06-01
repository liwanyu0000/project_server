package top.liwanyu.project_server.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import top.liwanyu.project_server.model.dto.BaseUserDto;
import top.liwanyu.project_server.model.dto.CommunicateDto;
import top.liwanyu.project_server.model.param.CommunicateParam;
import top.liwanyu.project_server.service.intf.CommunicateIntf;
import top.liwanyu.project_server.utils.TokenUtils;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/Communicate")
public class CommunicateController {

    @Autowired
    private CommunicateIntf communicateIntf;

    @Resource
    private TokenUtils tokenUtils;

    @PostMapping("/addCommunicate")
    public Boolean addCommunicate(@RequestBody CommunicateParam communicateParam, HttpServletRequest request) {
        Integer fromId = tokenUtils.getId(request.getHeader("token"));
        return communicateIntf.addCommunicate(fromId, communicateParam);
    }

    @PostMapping("/getCommunicate")
    public CommunicateDto getCommunicate(@RequestBody CommunicateParam communicateParam, HttpServletRequest request) {
        Integer fromId = tokenUtils.getId(request.getHeader("token"));
        return communicateIntf.getCommunicate(fromId, communicateParam);
    }

    @GetMapping("/getCommunicateList")
    public List<BaseUserDto> getCommunicateList(HttpServletRequest request) {
        Integer fromId = tokenUtils.getId(request.getHeader("token"));
        return communicateIntf.getCommunicateList(fromId);
    }

}
