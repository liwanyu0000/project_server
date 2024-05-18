package top.liwanyu.project_server.config;

import org.apache.ibatis.logging.stdout.StdOutImpl;
import org.mybatis.spring.boot.autoconfigure.ConfigurationCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MybatisConfig {
    @Bean
    public ConfigurationCustomizer configurationCustomizer() {
        return configuration -> configuration.setLogImpl(StdOutImpl.class);
    }
}
