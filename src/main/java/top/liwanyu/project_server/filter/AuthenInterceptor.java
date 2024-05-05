package top.liwanyu.project_server.filter;

import top.liwanyu.project_server.aspect.GlobalException;
import top.liwanyu.project_server.constant.enums.ResultStatus;
import top.liwanyu.project_server.utils.*;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class AuthenInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(@SuppressWarnings("null") HttpServletRequest request,
            @SuppressWarnings("null") HttpServletResponse response, @SuppressWarnings("null") Object handler)
            throws Exception {
        if (request.getRequestURI().contains("/user/login") || request.getRequestURI().contains("/user/register")
                || request.getRequestURI().contains("/test") || request.getRequestURI().contains("/error") || request.getRequestURI().contains("/verify")) {
            return true;
        }

        // 决定是否放行的问题
        String token = request.getHeader("token");
        if (StringUtils.isEmpty(token)) {
            // 再从请求参数获取一次
            token = request.getParameter("token");
            if (StringUtils.isEmpty(token)) {
                // 未登录
                throw new GlobalException(ResultStatus.NOT_EXISTS_TOKEN);
            }
        }

        // 有token
        // 验证token
        boolean result = JwtUtils.verify(token);
        // 验证未通过
        if (!result) {
            throw new GlobalException(ResultStatus.INVALID_TOKEN);
        }

        // // 校验通过的
        // // ①从token解析出用户的基本信息 userId，可以通过userId获取用户的信息 Admin对象 放在ThreadLocal中
        // UserDto user = JwtUtils.parse(token);
        // // 还要获取用户的权限信息 ，查出用户的权限信息，塞到admin中

        // 判断token是否需要进行续期
        boolean renewal = JwtUtils.renewal(token);
        if (renewal) {
            // String newToken = JwtUtils.token(user);
            // response.setHeader("new-token", newToken);
        }

        return true;
    }
}
