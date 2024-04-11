package top.liwanyu.project_server.utils;


public class ResultUtils {


    /**
     * 构造失败消息
     * @param code
     * @param msg
     * @return
     */
    @SuppressWarnings("rawtypes")
    public static Result buildFail(Integer code, String msg){
        Result result=new Result();
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }

    /**
     * 构建失败信息
     * @param messageCode
     * @return
     */
    @SuppressWarnings("rawtypes")
    public static Result buildFail(MessageCode messageCode){
         return buildFail(messageCode.getCode(),messageCode.getMsg());
    }


    /**
     * 构建列表的请求成功
     * @param data
     * @param count
     * @return
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public static Result buildSuccess(Object data,Long count){
        Result result=new Result();
        result.setCode(MessageCode.REQUEST_SUCCESS.getCode());
        result.setMsg(MessageCode.REQUEST_SUCCESS.getMsg());
        result.setData(data);
        result.setCount(count);
        return result;
    }


    /**
     * 构建请求成功单个数据
     * @param data
     * @return
     */
    @SuppressWarnings("rawtypes")
    public static Result buildSuccess(Object data){
        return buildSuccess(data,null);
    }

    /**
     * 构建没有携带数据的请求成功
     * @return
     */
    @SuppressWarnings("rawtypes")
    public static Result buildSuccess(){
        return buildSuccess(null,null);
    }



}
