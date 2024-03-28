/*
 * @Description: 
 * @Version: 
 * @Author: liwanyu
 * @Date: 2024-03-25 19:59:02
 * @LastEditors: liwanyu
 * @LastEditTime: 2024-03-28 10:57:00
 */
package top.liwanyu.project_server.model;
import lombok.Data;

@Data
public class TestTable {
    private int id;
    private String name;

    public TestTable(int id, String name) {
        this.id = id;
        this.name = name;
    }
}
