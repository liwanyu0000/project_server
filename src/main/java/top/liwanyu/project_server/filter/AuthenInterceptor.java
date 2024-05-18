// package top.liwanyu.project_server.filter;

// import top.liwanyu.project_server.utils.*;
// import org.springframework.stereotype.Component;
// import org.springframework.web.servlet.HandlerInterceptor;

// import jakarta.annotation.Resource;
// import jakarta.servlet.http.HttpServletRequest;
// import jakarta.servlet.http.HttpServletResponse;

// @Component
// public class AuthenInterceptor implements HandlerInterceptor {
    
//     @Resource
//     private TokenUtils tokenUtils;

//     @Override
//     public boolean preHandle(@SuppressWarnings("null") HttpServletRequest request,
//             @SuppressWarnings("null") HttpServletResponse response, @SuppressWarnings("null") Object handler)
//             throws Exception {
//         // if (request.getRequestURI().contains("/user/login") ||
//         // request.getRequestURI().contains("/user/register")
//         // || request.getRequestURI().contains("/test") ||
//         // request.getRequestURI().contains("/error") ||
//         // request.getRequestURI().contains("/verify")) {
//         // return true;
//         // }
//         if (request.getRequestURI().contains("/")) {
//             return true;
//         }

//         // 验证token
//         tokenUtils.getInfo(request.getHeader("token"));

//         return true;
//     }
// }
