package top.liwanyu.project_server.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import top.liwanyu.project_server.aspect.GlobalException;
import top.liwanyu.project_server.constant.enums.ResultStatus;
import top.liwanyu.project_server.model.dto.UserDto;
import top.liwanyu.project_server.model.param.UserParam;
import top.liwanyu.project_server.service.intf.UserIntf;
import top.liwanyu.project_server.service.intf.VerifyIntf;
import top.liwanyu.project_server.utils.TokenUtils;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    private UserIntf userIntf;
    @Resource
    private VerifyIntf verifyIntf;

    @Resource
    private TokenUtils tokenUtils;

    // 登录
    @PostMapping("/login")
    public UserDto login(@RequestBody UserParam userParam) {
        verifyIntf.checkVerifyCode(userParam.getCode(), userParam.getCodeToken());
        return userIntf.login(userParam);
    }

    // 通过token登录
    @GetMapping("/loginByToken")
    public UserDto loginByToken(HttpServletRequest request) {
        String token = request.getHeader("token");
        int userId = tokenUtils.getId(token);
        return userIntf.loginByToken(token, userId);
    }

    // 登出
    @DeleteMapping("/logout")
    public boolean logout(HttpServletRequest request) {
        tokenUtils.deleteToken(request.getHeader("token"));
        return true;
    }

    // 注册
    @PostMapping("/register")
    public boolean register(@RequestBody UserParam userParam) {
        verifyIntf.checkVerifyCode(userParam.getCode(), userParam.getCodeToken());
        return userIntf.register(userParam);
    }

    // 改密码
    @PostMapping("/changePwd")
    public boolean changePwd(@RequestBody UserParam userParam, HttpServletRequest request) {
        int userId = tokenUtils.getId(request.getHeader("token"));
        verifyIntf.checkVerifyCode(userParam.getCode(), userParam.getCodeToken());
        userParam.setId(userId);
        return userIntf.changePwd(userParam);
    }

    // 更改用户信息
    @PostMapping("/changeInfo")
    public boolean changeInfo(@RequestBody UserParam userParam, HttpServletRequest request) {
        int userId = tokenUtils.getId(request.getHeader("token"));
        userParam.setId(userId);
        return userIntf.changeInfo(userParam);
    }

    // 修改用户权限
    @PostMapping("/changePermission")
    public boolean changePermission(@RequestBody UserParam userParam, HttpServletRequest request) {
        int userId =  tokenUtils.verifyAdmin( request.getHeader("token"));
        if (userParam.getId() ==  null) {
            throw new GlobalException(ResultStatus.USER_ID_NOT_NULL);
        }
        if ( userParam.getId() ==  userId) {
            throw new GlobalException(ResultStatus.CANNOT_CHANGE_SELF_PERMISSION);
        }
        return userIntf.changePermission(userParam);
    }

    // 获取用户信息
    @PostMapping("/filterUsers")
    public List<UserDto> filterUsers(@RequestBody UserParam userParam, HttpServletRequest request) {
        int id = tokenUtils.verifyAdmin( request.getHeader("token"));
        userParam.setId(id);
        return userIntf.filterUsers(userParam);
    }

}
