/*
 * @Description: 
 * @Version: 
 * @Author: liwanyu
 * @Date: 2024-04-10 09:54:54
 * @LastEditors: liwanyu
 * @LastEditTime: 2024-04-10 09:57:58
 */
package top.liwanyu.project_server.aspect;

import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
@EqualsAndHashCode(callSuper=false)
public class GlobalException extends RuntimeException{
    private IResultStatus resultStatus;

    public GlobalException(){
        super();
    }
    
    public GlobalException(String msg){
        super(msg);
    }
    public GlobalException(Throwable t){
        super(t);
    }
    public GlobalException(IResultStatus resultStatus){
        this.resultStatus = resultStatus;
    }
}
