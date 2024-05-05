package top.liwanyu.project_server.constant.enums;

import top.liwanyu.project_server.aspect.IResultStatus;

/**
 * 状态码枚举
 */

public enum ResultStatus implements IResultStatus {
    FAIL(-1, ""),
    SUCCESS(200, "请求成功"),
    BAD_REQUEST(400, "请求参数有误"),
    UNAUTHORIZED(401, "认证异常"),
    NOT_FOUND(404, "资源不存在"),
    INTERNAL_SERVER_ERROR(500, "服务器内部错误"),
    SERVER_BUSY(503, "服务器繁忙，请稍后再试"),
    PARAM_ERROR(10001, "参数错误"),
    NOT_EXISTS_TOKEN(10002, "未携带token"),
    INVALID_TOKEN(10003, "无效的token"),
    USER_NOT_FOUND(10004, "用户不存在"),
    PASSWORD_ERROR(10005, "密码错误"),
    JSON_PARSE_ERROR(10006, "json解析错误"),
    REGISTER_ERROR(10007, "注册失败"),
    USER_EXISTS(10008, "用户已存在"),
    USERNAME_NOT_NULL(10009, "用户名不能为空"),
    PASSWORD_NOT_NULL(10010, "密码不能为空"),
    VERIFY_CODE_ERROR(10011, "验证码错误"),
    VERIFY_CODE_EXPIRED(10012, "验证码已过期"),
    VERIFY_CODE_NOT_NULL(10013, "验证码不能为空"),
    PASSWORD_VERIFY_ERROR(10014, "两次密码输入不一致"),
    ;

    // 状态码
    private Integer code;

    // 信息描述
    private String msg;

    private ResultStatus(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    @Override
    public Integer code() {
        return code;
    }

    @Override
    public String msg() {
        return msg;
    }
}