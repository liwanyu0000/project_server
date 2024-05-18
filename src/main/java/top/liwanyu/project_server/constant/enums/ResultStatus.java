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
    FILE_UPLOAD_ERROR(10015, "文件上传失败"),
    FILE_NOT_NULL(10016, "文件不能为空"),
    FILE_NAME_ERROR(10017, "文件名错误"),
    FILE_TYPE_ERROR(10018, "文件类型错误"),
    REQUEST_PARAM_ERROR(10019, "请求参数错误"),
    REQUEST_METHOD_ERROR(10020, "请求方法错误"),
    PASSWORD_VERIFY_ERROR2(10021, "旧密码输入错误"),
    PASSWORD_CHANGE_ERROR(10022, "密码修改失败"),
    OLD_PASSWORD_NOT_NULL(10023, "旧密码不能为空"),
    HOUSE_INFO_NOT_NULL(10024, "房屋信息不能为空"),
    HOUSE_ADD_ERROR(10025, "房屋添加失败"),
    NOT_IS_ADMIN(10026, "非管理员"),
    NOT_CAN_LOGIN(10027, "无法登录"),
    CANNOT_CHANGE_SELF_PERMISSION(10028, "无法更改自己的权限"),
    USER_ID_NOT_NULL(10029, "用户id不能为空"),
    USER_INFO_CHANGE_ERROR(10030, "用户信息修改失败"),
    USER_PERMISSION_CHANGE_ERROR(10031, "用户权限修改失败"),
    NOT_EXISTS_HOUSE(10032, "房屋不存在"),
    NOT_UPDATE_OTHER_HOUSE(10033, "无法修改他人的房屋"),
    HOUSE_TRADE_TYPE_NOT_NULL(10034, "房屋交易类型不能为空"),
    HOUSE_STATUS_NOT_NULL(10035, "房屋状态不能为空"),
    HOUSE_TERRITORY_NOT_NULL(10036, "房屋地域不能为空"),
    HOUSE_PRICE_NOT_NULL(10037, "价格不能为空"),
    HOUSE_ADDRCODE_NOT_NULL(10038, "房屋地址码不能为空"),
    HOUSE_ADDR_NOT_NULL(10039, "房屋详细地址不能为空"),
    
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