package top.liwanyu.project_server.utils;

/**
 * 返回的提示信息
 */
public enum MessageCode {

    //配置提示信息
    FILED_SAVE_ERROR(50012,"图片保存失败"),
    ID_NOTE_EMPTY(10001,"亲，您没有传id"),
    REQUEST_SUCCESS(0,"请求成功"),
    SYSTEM_ERROR(50000,"对不起，系统开小差了!"),
    IMAGE_NOT(50001,"图片格式不正确"),
    FILED_NOT_SUCCESS(50002,"上传文件的字段名你不要乱传，必须叫image"),
    METHOD_NOT_EXISTS(50003,"调用的方法不存在!"),
    PHONE_NOT_EXISTS(50004,"手机号码不存在!"),
    SMS_EXPIRE(50005,"验证码失效!"),
    SMS_ERROR(50006,"验证码输入错误!"),
    NO_PERMISSION(50007,"你没有权限!"),
    NOT_EXISTS_TOKEN(50008,"未携带token"),
    INVALID_TOKEN(50009,"无效的token"),
    FAIL_LOGIN(50010,"用户名或密码不正确!"),
    FAIL_UPDATE(50011,"操作失败");

    private Integer code;
    private String msg;

    private MessageCode(Integer code, String msg){
        this.code=code;
        this.msg=msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
