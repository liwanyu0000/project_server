package top.liwanyu.project_server.interceptor;

import top.liwanyu.project_server.exception.GlobalException;
import top.liwanyu.project_server.model.User;
import top.liwanyu.project_server.utils.*;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * 登录拦截器
 * @author DaiKaiQi
 * @date 2024/03/26 11:01
 **/
@Component
public class AuthenInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(@SuppressWarnings("null") HttpServletRequest request, @SuppressWarnings("null") HttpServletResponse response, @SuppressWarnings("null") Object handler) throws Exception {

        //决定是否放行的问题
        String token = request.getHeader("token");
        if(StringUtils.isEmpty(token)) {
            //再从请求参数获取一次
            token = request.getParameter("token");
            if(StringUtils.isEmpty(token)) {
                //未登录
                System.out.println("未携带token");
                throw new GlobalException(MessageCode.NOT_EXISTS_TOKEN);
            }
        }

        //有token
        //验证token
        boolean result = JwtUtils.verify(token);
        //验证未通过
        if(!result){
            throw new GlobalException(MessageCode.INVALID_TOKEN);
        }

        //校验通过的
        //①从token解析出用户的基本信息 userId，可以通过userId获取用户的信息  Admin对象  放在ThreadLocal中
        User user = JwtUtils.parse(token);

        System.out.println(user);
        //还要获取用户的权限信息 ，查出用户的权限信息，塞到admin中

        //判断token是否需要进行续期
        boolean renewal = JwtUtils.renewal(token);
        if (renewal){
            String newToken = JwtUtils.token(user);
            response.setHeader("new-token", newToken);
        }

        return true;
    }
}
