package top.liwanyu.project_server.mapper;

import top.liwanyu.project_server.model.entity.UserEntity;

public interface UserMapper {
    public UserEntity login(String username);

    public Integer register(String username, String password, String nickName, String phone, String email, String avatar,
            String addr, String userRole, String userPermission, String pwsalt, String createTime, String updateTime);

    public Integer findUser(String username);
}
