package top.liwanyu.project_server.service.impl;

import java.util.Map;

import org.springframework.stereotype.Component;

import jakarta.annotation.Resource;
import top.liwanyu.project_server.aspect.GlobalException;
import top.liwanyu.project_server.constant.enums.ResultStatus;
import top.liwanyu.project_server.mapper.UserMapper;
import top.liwanyu.project_server.model.dto.UserDto;
import top.liwanyu.project_server.model.entity.UserEntity;
import top.liwanyu.project_server.service.intf.UserIntf;
import top.liwanyu.project_server.utils.DateUtils;
import top.liwanyu.project_server.utils.JwtUtils;
import top.liwanyu.project_server.utils.Md5Utils;

@Component
public class UserImpl implements UserIntf {

    @Resource
    private UserMapper userMapper;

    @Override
    public UserDto login(Map<String, String> map) {
        String userName = map.get("username");
        if (userName == null || userName.isEmpty()) {
            throw new GlobalException(ResultStatus.USERNAME_NOT_NULL);
        }
        UserEntity userEntity = userMapper.login(userName);
        if (userEntity == null) {
            throw new GlobalException(ResultStatus.USER_NOT_FOUND);
        }
        String password = map.get("password");
        if (password == null || password.isEmpty()) {
            throw new GlobalException(ResultStatus.PASSWORD_NOT_NULL);
        }
        if (!userEntity.getPassWord().equals(Md5Utils.md5Password(password, userEntity.getPwsalt()))) {
            throw new GlobalException(ResultStatus.PASSWORD_ERROR);
        }
        UserDto user = new UserDto();
        user.setUserName(userEntity.getUserName());
        user.setNickName(userEntity.getNickName());
        user.setPhone(userEntity.getPhone());
        user.setEmail(userEntity.getEmail());
        user.setAvatar(userEntity.getAvatar());
        user.setAddr(userEntity.getAddr());
        user.setToken(JwtUtils.token(userEntity));
        user.setCreateTime(userEntity.getCreateTime());
        user.setUpdateTime(userEntity.getUpdateTime());
        user.setUserRole(userEntity.getUserRole());
        user.setUserPermission(userEntity.getUserPermission());
        return user;
    }

    @Override
    public boolean register(Map<String, String> map) {
        String username = map.get("username");
        if (username == null || username.isEmpty()) {
            throw new GlobalException(ResultStatus.USERNAME_NOT_NULL);
        }
        if (userMapper.findUser(username) >= 1) {
            throw new GlobalException(ResultStatus.USER_EXISTS);
        }
        String password = map.get("password");
        if (password == null || password.isEmpty()) {
            throw new GlobalException(ResultStatus.PASSWORD_NOT_NULL);
        }
        String sPassword = map.get("sPassword");
        if (sPassword == null || sPassword.isEmpty() || !sPassword.equals(password)) {
            throw new GlobalException(ResultStatus.PASSWORD_VERIFY_ERROR);
        }
        String pwSalt = Md5Utils.createSalt();
        String md5Password = Md5Utils.md5Password(password, pwSalt);
        String nickName = map.get("nickName");
        if (nickName == null || nickName.isEmpty()) {
            nickName = username;
        }
        String phone = map.get("phone");
        if (phone == null || phone.isEmpty()) {
            phone = null;
        }
        String email = map.get("email");
        if (email == null || email.isEmpty()) {
            email = null;
        }
        String avatar = map.get("avatar");
        if (avatar == null || avatar.isEmpty()) {
            avatar = null;
        }
        String addr = map.get("addr");
        if (addr == null || addr.isEmpty()) {
            addr = null;
        }
        String userRole = map.get("userRole");
        if (userRole == null || userRole.isEmpty()) {
            userRole = "User";
        }
        String userPermission = map.get("userPermission");
        if (userPermission == null || userPermission.isEmpty()) {
            userPermission = "login, create";
        }

        String createTime = DateUtils.getNowDate();
        String updateTime = DateUtils.getNowDate();
        Integer res = userMapper.register(username, md5Password, nickName, phone, email, avatar, addr, userRole,
                userPermission, pwSalt,
                createTime, updateTime);
        if (res < 1) {
            throw new GlobalException(ResultStatus.REGISTER_ERROR);
        }
        return true;

    }

}
