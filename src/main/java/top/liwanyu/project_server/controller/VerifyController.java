package top.liwanyu.project_server.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import jakarta.annotation.Resource;
import top.liwanyu.project_server.model.dto.VerifyDto;
import top.liwanyu.project_server.service.intf.VerifyIntf;

@RestController
@RequestMapping("/verify")
public class VerifyController {
    
    @Resource
    private VerifyIntf verifyIntf;

    @GetMapping("/getCode")
    public VerifyDto getVerifyCode() {
       return verifyIntf.getVerifyCode();
    }

}
