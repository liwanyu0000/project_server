/*
 * @Description: 
 * @Version: 
 * @Author: liwanyu
 * @Date: 2024-03-28 10:57:16
 * @LastEditors: liwanyu
 * @LastEditTime: 2024-03-28 10:58:05
 */
package top.liwanyu.project_server.repo;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.annotation.Resource;
import top.liwanyu.project_server.mapper.TestTableMapper;
import top.liwanyu.project_server.model.TestTable;


@RestController
@RequestMapping("/api")
public class TestTableRepo {
    @Resource	
    private	TestTableMapper testTableMapper;

    @GetMapping("/你看")
    public String test(){
        return "你看空气";
    }

    @GetMapping("/All")
    public List<TestTable> getData() {
        return testTableMapper.selectAll();
    }

    @GetMapping("/getMethodName/{id}")
    public String getMethodName(@PathVariable int id) {
        return testTableMapper.selectByPrimaryKey(id);
    }
    


}
