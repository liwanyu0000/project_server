/*
 * @Description: 
 * @Version: 
 * @Author: liwanyu
 * @Date: 2024-03-28 10:39:29
 * @LastEditors: liwanyu
 * @LastEditTime: 2024-03-28 10:59:35
 */
package top.liwanyu.project_server;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("top.liwanyu.project_server.mapper")
public class ProjectServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProjectServerApplication.class, args);
    }

}
