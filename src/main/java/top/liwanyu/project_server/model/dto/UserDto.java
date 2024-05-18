/*
 * @Description: 
 * @Version: 
 * @Author: liwanyu
 * @Date: 2024-04-10 10:07:11
 * @LastEditors: liwanyu
 * @LastEditTime: 2024-04-10 10:09:22
 */
package top.liwanyu.project_server.model.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
@EqualsAndHashCode(callSuper=true)
public class UserDto extends BaseUserDto{
    // 用户名
    private String userName;
    // 创建时间
    private String createTime;
    // 更新时间
    private String updateTime;
    // 权限
    private String userPermission;  
    // 额外的信息
    private String extraInfo;
    // 用户手机号
    private String phones;
    // 用户邮箱
    private String emails;
}
