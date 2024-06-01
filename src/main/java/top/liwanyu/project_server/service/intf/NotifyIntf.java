package top.liwanyu.project_server.service.intf;


import top.liwanyu.project_server.model.dto.NotifyDto;
import top.liwanyu.project_server.model.dto.HouseDto;
import top.liwanyu.project_server.model.dto.UserDto;

public interface NotifyIntf {
    // 未登录时, 返回一个公共通道
    public NotifyDto createChannel();

    // 登录时, 返回一个私有通道
    public NotifyDto createChannel(Integer id);

    // 发送消息(私有)
    public Boolean sendNotify(String data, Integer to, Integer from);

    // 发送消息
    public Boolean sendNotify(HouseDto userDto);

     // 发送消息
     public Boolean sendNotify(UserDto houseDto);

    // 保持心跳
    public Boolean keepHeartbeat(String channel);

    // 删除通道
    public Boolean deleteChannel(String channel);
}
