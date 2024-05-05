package top.liwanyu.project_server.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.annotation.Resource;
import top.liwanyu.project_server.model.dto.UserDto;
import top.liwanyu.project_server.service.intf.UserIntf;
import top.liwanyu.project_server.service.intf.VerifyIntf;

import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    private UserIntf userIntf;
    @Resource
    private VerifyIntf verifyIntf;

    @PostMapping("/login")
    public UserDto login(@RequestBody Map<String, String> map) {
        verifyIntf.checkVerifyCode(map.get("code"), map.get("codeToken"));
        return userIntf.login(map);
    }

    @PostMapping("/register")
    public boolean register(@RequestBody Map<String, String> map) {
        verifyIntf.checkVerifyCode(map.get("code"), map.get("codeToken"));
        return userIntf.register(map);
    }

}
