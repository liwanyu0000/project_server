package top.liwanyu.project_server.service.impl;


import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import io.netty.util.internal.StringUtil;
import jakarta.annotation.Resource;
import top.liwanyu.project_server.aspect.GlobalException;
import top.liwanyu.project_server.constant.consist.User;
import top.liwanyu.project_server.constant.enums.ResultStatus;
import top.liwanyu.project_server.mapper.UserMapper;
import top.liwanyu.project_server.model.dto.LoginUserDto;
import top.liwanyu.project_server.model.dto.UserDto;
import top.liwanyu.project_server.model.entity.UserEntity;
import top.liwanyu.project_server.model.param.UserParam;
import top.liwanyu.project_server.model.query.UserQuery;
import top.liwanyu.project_server.service.intf.NotifyIntf;
import top.liwanyu.project_server.service.intf.UserIntf;
import top.liwanyu.project_server.utils.BeanCopyUtils;
import top.liwanyu.project_server.utils.DateUtils;
import top.liwanyu.project_server.utils.TokenUtils;
import top.liwanyu.project_server.utils.Md5Utils;

@Component
public class UserImpl implements UserIntf {

    @Resource
    private UserMapper userMapper;

    
    @Resource
    private TokenUtils tokenUtils;

    
    @Resource
    private NotifyIntf notifyIntf;

    @Override
    public LoginUserDto login(UserParam userParam) {
        String userName = userParam.getUserName();
        if (StringUtil.isNullOrEmpty(userName)) {
            throw new GlobalException(ResultStatus.USERNAME_NOT_NULL);
        }
        UserEntity userEntity = userMapper.login(userName);
        if (userEntity == null) {
            throw new GlobalException(ResultStatus.USER_NOT_FOUND);
        }
        if (!userEntity.canLogin()) {
            throw new GlobalException(ResultStatus.NOT_CAN_LOGIN);
        }
        String password = userParam.getPassword();
        if (StringUtil.isNullOrEmpty(password)) {
            throw new GlobalException(ResultStatus.PASSWORD_NOT_NULL);
        }
        if (!userEntity.getPassword().equals(Md5Utils.md5Password(password, userEntity.getPwsalt()))) {
            throw new GlobalException(ResultStatus.PASSWORD_ERROR);
        }
        LoginUserDto user = BeanCopyUtils.copyBean(userEntity, LoginUserDto.class);
        user.setToken(tokenUtils.createToken(user.getId()));
        return user;
    }

    @Override
    public LoginUserDto loginByToken(String token, int userId) {
        if (StringUtil.isNullOrEmpty(token)) {
            throw new GlobalException(ResultStatus.NOT_EXISTS_TOKEN);
        }
        UserEntity userEntity = userMapper.findUserById(userId);
        if (userEntity == null) {
            throw new GlobalException(ResultStatus.USER_NOT_FOUND);
        }
        if (!userEntity.canLogin()) {
            throw new GlobalException(ResultStatus.NOT_CAN_LOGIN);
        }
        LoginUserDto user = BeanCopyUtils.copyBean(userEntity, LoginUserDto.class);
        user.setToken(tokenUtils.createToken(user.getId()));
        return user;
    }

    @Override
    public boolean register(UserParam userParam) {
        // 判断用户名是否为空
        String userName = userParam.getUserName();
        if (StringUtil.isNullOrEmpty(userName)) {
            throw new GlobalException(ResultStatus.USERNAME_NOT_NULL);
        }
        // 判断用户名是否存在
        if (userMapper.haveUser(userName) >= 1) {
            throw new GlobalException(ResultStatus.USER_EXISTS);
        }
        // 判断密码是否为空
        String password = userParam.getPassword();
        if (StringUtil.isNullOrEmpty(password)) {
            throw new GlobalException(ResultStatus.PASSWORD_NOT_NULL);
        }
        // 判断两次密码是否一致
        String sPassword = userParam.getSecondPassword();
        if (StringUtil.isNullOrEmpty(sPassword) || !sPassword.equals(password)){
            throw new GlobalException(ResultStatus.PASSWORD_VERIFY_ERROR);
        }
        // 转换为UserQuery
        UserQuery userQuery = BeanCopyUtils.copyBean(userParam, UserQuery.class);
        
        // 加密
        String salt = Md5Utils.createSalt();
        userQuery.setPwsalt(salt);
        userQuery.setPassword(Md5Utils.md5Password(password, salt));
        
        // 昵称为空时写入用户名
        if (StringUtil.isNullOrEmpty(userQuery.getNickName())) {
            userQuery.setNickName(userQuery.getUserName());
        }
        // 写入默认权限
        userQuery.setUserPermission(User.USER_PERMISSION_DEFAULT);
        // 写入时间
        userQuery.setCreateTime(DateUtils.getNowDate());
        userQuery.setUpdateTime(DateUtils.getNowDate());

        // 写入数据库
        Integer res = userMapper.register(userQuery);
        if (res < 1) {
            throw new GlobalException(ResultStatus.REGISTER_ERROR);
        }
        return true;

    }

    @Override
    public boolean changePwd(UserParam userParam) {
        Integer id = userParam.getId();
        if (id == null) {
            throw new GlobalException(ResultStatus.REQUEST_PARAM_ERROR);
        }
        UserEntity userEntity = userMapper.findUserById(id);
        if (userEntity == null) {
            throw new GlobalException(ResultStatus.USER_NOT_FOUND);
        }
        String oldPwd = userParam.getOldPassword();
        if (StringUtil.isNullOrEmpty(oldPwd)) {
            throw new GlobalException(ResultStatus.OLD_PASSWORD_NOT_NULL);
        }
        String pwSalt = userEntity.getPwsalt();
        if (!Md5Utils.md5Password(oldPwd, pwSalt).equals(userEntity.getPassword())) {
            throw new GlobalException(ResultStatus.PASSWORD_VERIFY_ERROR2);
        }
        String newPwd = userParam.getPassword();
        if (StringUtil.isNullOrEmpty(newPwd)) {
            throw new GlobalException(ResultStatus.PASSWORD_NOT_NULL);
        }
        String sNewPwd = userParam.getSecondPassword();
        if (StringUtil.isNullOrEmpty(sNewPwd) || !sNewPwd.equals(newPwd)) {
            throw new GlobalException(ResultStatus.PASSWORD_VERIFY_ERROR);
        }
        if (userMapper.changePwd(id, Md5Utils.md5Password(newPwd, pwSalt),
                DateUtils.getNowDate()) <= 0) {
            throw new GlobalException(ResultStatus.PASSWORD_CHANGE_ERROR);
        }
        return true;
    }

    @Override
    public boolean changeInfo(UserParam userParam) {
        UserQuery userQuery = BeanCopyUtils.copyBean(userParam, UserQuery.class);
        userQuery.setUpdateTime(DateUtils.getNowDate());
        if (userMapper.changeInfo(userQuery) < 1)
        {
            throw new GlobalException(ResultStatus.USER_INFO_CHANGE_ERROR);
        }
        notifyIntf.sendNotify(BeanCopyUtils.copyBean(userMapper.findUserById(userQuery.getId()), UserDto.class));
        return true;
    }

    @Override
    public List<UserDto> filterUsers(UserParam userParam) {
        List<UserEntity> userEntitys = userMapper.findUser(BeanCopyUtils.copyBean(userParam, UserQuery.class));
        List<UserDto> userDtos = new ArrayList<>(); // Initialize userDtos as an empty ArrayList
        for (UserEntity userEntity : userEntitys) {
            userDtos.add(BeanCopyUtils.copyBean(userEntity, UserDto.class));
        }
        return userDtos;
    }

    @Override
    public boolean changePermission(UserParam userParam) {
        UserQuery userQuery = BeanCopyUtils.copyBean(userParam, UserQuery.class);
        userQuery.setUpdateTime(DateUtils.getNowDate());
        if ( userMapper.changePermission(userQuery) < 1) {
            throw new GlobalException(ResultStatus.USER_PERMISSION_CHANGE_ERROR);
        }
        notifyIntf.sendNotify(BeanCopyUtils.copyBean(userMapper.findUserById(userQuery.getId()), UserDto.class));
        return true;
    }

}
