package top.liwanyu.project_server.service.intf;


import java.util.List;

import top.liwanyu.project_server.model.dto.LoginUserDto;
import top.liwanyu.project_server.model.dto.UserDto;
import top.liwanyu.project_server.model.param.UserParam;

public interface UserIntf {
    public LoginUserDto login(UserParam userParam);

    public LoginUserDto loginByToken(String token, int userId);

    public boolean register(UserParam userParam);

    public boolean changePwd(UserParam userParam);

    public boolean changeInfo(UserParam userParam);

    public boolean changePermission(UserParam userParam);

    public List<UserDto> filterUsers(UserParam userParam);
}
