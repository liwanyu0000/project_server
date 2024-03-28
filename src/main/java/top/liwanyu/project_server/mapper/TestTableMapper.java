/*
 * @Description: 
 * @Version: 
 * @Author: liwanyu
 * @Date: 2024-03-25 20:02:03
 * @LastEditors: liwanyu
 * @LastEditTime: 2024-03-28 10:54:34
 */
package top.liwanyu.project_server.mapper;

import java.util.List;

import top.liwanyu.project_server.model.TestTable;

public interface TestTableMapper {
      List<TestTable> selectAll();
      String selectByPrimaryKey(int id);
}
