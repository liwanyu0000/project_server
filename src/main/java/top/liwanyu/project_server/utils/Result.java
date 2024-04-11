package top.liwanyu.project_server.utils;

import lombok.Data;

@Data
public class Result<T> {

    //提示编码
    private Integer code;

    //提示信息
    private String msg;

    //总记录数
    private Long count;

    //携带的数据
    private T data;

}