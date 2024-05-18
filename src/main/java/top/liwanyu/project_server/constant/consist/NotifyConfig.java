package top.liwanyu.project_server.constant.consist;

public class NotifyConfig {
    // 通道前缀
    static public final String CHANNELPREFIX = "CHANNEL_";
    // 普通通道格式 随机码4位（生成重复的无任何影响）
    static public final String NORMALCHANNEL = CHANNELPREFIX + "%04d";
    // 登录通道格式 随机码4位 用户id8位 （用户id保证唯一）
    static public final String LOGINCHANNEL = CHANNELPREFIX + "%04d_%08d";

    // 通道过期时间(8分钟)
    static public final long CHANNELTIMEOUT = 1000  * 60 * 8;

    // 通道集合
    static public final String CHANNELSET = "CHANNEL_SET";

    // 消息前缀
    static public final String MESSAGEPREFIX = "MESSAGE_";

    // 获取id
    static public Integer getId(String channel) {
        if (channel == null) return null;
        String[] split = channel.split("_");
        if (split.length == 3) {
            return Integer.parseInt(split[2]);
        }
        return null;
    }
    
    

}
