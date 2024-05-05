package top.liwanyu.project_server.service.intf;

import java.util.Map;

import top.liwanyu.project_server.model.dto.UserDto;

public interface UserIntf {
    public UserDto login(Map<String, String> map);

    public boolean register(Map<String, String> map);
}
