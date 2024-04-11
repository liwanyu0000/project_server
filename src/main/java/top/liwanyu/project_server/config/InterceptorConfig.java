package top.liwanyu.project_server.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import top.liwanyu.project_server.interceptor.AuthenInterceptor;

@Configuration
public class InterceptorConfig  implements WebMvcConfigurer{
    @Autowired
    private AuthenInterceptor authenInterceptor;

    //将拦截器
    @Override
    public void addInterceptors(@SuppressWarnings("null") InterceptorRegistry registry) {
        registry.addInterceptor(authenInterceptor)
                .addPathPatterns("/**") //拦截所有的 url
                .excludePathPatterns("/**/user/login")//排除url: /user/login (登录)
                .excludePathPatterns("/**/user/reg") //排除url: /user/reg   (注册)
                .excludePathPatterns("/**/test/All/*")//排除url: /All
                .excludePathPatterns("/**/test/show");//排除url: /你看;
    }

    // 添加统一前缀api
    @Override
    public void configurePathMatch(@SuppressWarnings("null") PathMatchConfigurer configurer) {
        configurer.addPathPrefix("api", c -> true);
        WebMvcConfigurer.super.configurePathMatch(configurer);
    }
}
