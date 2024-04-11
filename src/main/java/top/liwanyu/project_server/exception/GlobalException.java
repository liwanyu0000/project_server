/*
 * @Description: 
 * @Version: 
 * @Author: liwanyu
 * @Date: 2024-04-10 09:54:54
 * @LastEditors: liwanyu
 * @LastEditTime: 2024-04-10 09:57:58
 */
package top.liwanyu.project_server.exception;

import top.liwanyu.project_server.utils.MessageCode;
import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
@EqualsAndHashCode(callSuper=false)
public class GlobalException extends RuntimeException{
    private MessageCode messageCode;

    public GlobalException(){
        super();
    }
    public GlobalException(String msg){
        super(msg);
    }
    public GlobalException(Throwable t){
        super(t);
    }
    public GlobalException(MessageCode messageCode){
        this.messageCode = messageCode;
    }
}
