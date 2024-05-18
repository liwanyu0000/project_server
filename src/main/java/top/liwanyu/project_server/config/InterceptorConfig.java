package top.liwanyu.project_server.config;

// import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import top.liwanyu.project_server.constant.consist.FileConfig;
// import top.liwanyu.project_server.filter.AuthenInterceptor;

@Configuration
public class InterceptorConfig  implements WebMvcConfigurer{
    // @Autowired
    // private AuthenInterceptor authenInterceptor;

    @Override
    public void addInterceptors(@SuppressWarnings("null") InterceptorRegistry registry) {
        // registry.addInterceptor(authenInterceptor).addPathPatterns("/**");    
    }

    // 添加统一前缀api
    @Override
    public void configurePathMatch(@SuppressWarnings("null") PathMatchConfigurer configurer) {
        configurer.addPathPrefix("api", c -> true);
        WebMvcConfigurer.super.configurePathMatch(configurer);
    }

    @Override
    public void addResourceHandlers(@SuppressWarnings("null") ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/api/files/**").addResourceLocations("file:///" + FileConfig.FILE_PATH);
        registry.addResourceHandler("/api/files/img/**").addResourceLocations("file:///" + FileConfig.IMG_FILE_PATH);
        registry.addResourceHandler("/api/files/video/**").addResourceLocations("file:///" + FileConfig.VIDEO_FILE_PATH);
        registry.addResourceHandler("/api/files/other/**").addResourceLocations("file:///" + FileConfig.other_FILE_PATH);
        WebMvcConfigurer.super.addResourceHandlers(registry);
    }

   
}
