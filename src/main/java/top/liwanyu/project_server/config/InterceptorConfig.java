package top.liwanyu.project_server.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import top.liwanyu.project_server.filter.AuthenInterceptor;

@Configuration
public class InterceptorConfig  implements WebMvcConfigurer{
    @Autowired
    private AuthenInterceptor authenInterceptor;

    @Override
    public void addInterceptors(@SuppressWarnings("null") InterceptorRegistry registry) {
        registry.addInterceptor(authenInterceptor);    
    }

    // 添加统一前缀api
    @Override
    public void configurePathMatch(@SuppressWarnings("null") PathMatchConfigurer configurer) {
        configurer.addPathPrefix("api", c -> true);
        WebMvcConfigurer.super.configurePathMatch(configurer);
    }

    @Override
    public void addResourceHandlers(@SuppressWarnings("null") ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/api/files/**").addResourceLocations("file:///C:/Shared Files/");
        WebMvcConfigurer.super.addResourceHandlers(registry);
    }

   
}
