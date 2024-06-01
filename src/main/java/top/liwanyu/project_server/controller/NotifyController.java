package top.liwanyu.project_server.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.netty.util.internal.StringUtil;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import top.liwanyu.project_server.model.dto.NotifyDto;
// import top.liwanyu.project_server.model.param.MessageParam;
import top.liwanyu.project_server.service.intf.NotifyIntf;
import top.liwanyu.project_server.utils.TokenUtils;

import org.springframework.web.bind.annotation.DeleteMapping;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;



@RestController
@RequestMapping("/notify")
public class NotifyController {

    @Resource
    private NotifyIntf notifyIntf;

    @Resource
    private TokenUtils tokenUtils;


    // @PostMapping("/send")
    // public boolean send(@RequestBody MessageParam messageParam, HttpServletRequest request) {
    //     if (messageParam.getTo() == null) {
    //         return notifyIntf.sendNotify(messageParam);
    //     }
    //     int userId = tokenUtils.getId(request.getHeader("token"));
    //     return notifyIntf.sendNotify(messageParam, userId);
    // }

    @GetMapping("/create")
    public NotifyDto creat(HttpServletRequest request) {
        String token = request.getHeader("token");
        if (StringUtil.isNullOrEmpty(token)) {
            return notifyIntf.createChannel();
        }
        int userId = tokenUtils.getId(token);
        return notifyIntf.createChannel(userId);
    }

    @GetMapping("/keep/{channel}")
    public boolean keep(@PathVariable String channel) {
        return notifyIntf.keepHeartbeat(channel);
    }



    @DeleteMapping("/delete/{channel}")
    public boolean delete(@PathVariable String channel) {
       return notifyIntf.deleteChannel(channel);
    }
    

}
