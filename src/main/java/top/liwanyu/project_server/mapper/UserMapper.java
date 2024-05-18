package top.liwanyu.project_server.mapper;

import top.liwanyu.project_server.model.entity.UserEntity;
import top.liwanyu.project_server.model.query.UserQuery;

import java.util.List;

public interface UserMapper {
    public UserEntity login(String username);

    public UserEntity findUserById(int id);

    public Integer haveUser(String userName);

    public Integer register(UserQuery userQuery);

    public List<UserEntity> findUser(UserQuery userQuery);

    public Integer changePwd(Integer id, String password, String updateTime);

    public Integer changeInfo(UserQuery userQuery);

    public Integer changePermission(UserQuery userQuery);
}
