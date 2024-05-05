/*
 * @Description: 
 * @Version: 
 * @Author: liwanyu
 * @Date: 2024-03-28 10:57:16
 * @LastEditors: liwanyu
 * @LastEditTime: 2024-04-11 10:49:04
 */
package top.liwanyu.project_server.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.annotation.Resource;
import top.liwanyu.project_server.mapper.TestTableMapper;

@RestController
@RequestMapping("/test")
public class TestTableRepo {
    @Resource
    private TestTableMapper testTableMapper;

    @GetMapping("/show")
    public String test() {
        return "你看空气";
    }

    @GetMapping("/All/{name}")
    public String getData(@PathVariable String name) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
        System.err.println(sdf.format(new Date(System.currentTimeMillis())) + ":   " + name + "  请求了接口");
        return "Successfully accessed the interface '" + name + "'!";
    }

    @GetMapping("/getMethodName/{id}")
    public String getMethodName(@PathVariable int id) {
        return testTableMapper.selectByPrimaryKey(id);
    }

}
