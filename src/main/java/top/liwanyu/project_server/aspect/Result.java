package top.liwanyu.project_server.aspect;

import lombok.Data;
import top.liwanyu.project_server.constant.enums.ResultStatus;

@Data
public class Result<T> {
    // 提示编码
    private Integer code;

    // 提示信息
    private String msg;

    // 携带的数据
    private T data;

    private Result(IResultStatus resultStatus, T data) {
        this.code = resultStatus.code();
        this.msg = resultStatus.msg();
        this.data = data;
    }

    private Result(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    /**
     * 业务成功返回业务代码和描述信息
     */
    public static Result<Void> success() {
        return new Result<Void>(ResultStatus.SUCCESS, null);
    }

    /**
     * 业务成功返回业务代码,描述和返回的参数
     */
    public static <T> Result<T> success(T data) {
        return new Result<T>(ResultStatus.SUCCESS, data);
    }

    /**
     * 业务成功返回业务代码,描述和返回的参数
     */
    public static <T> Result<T> success(IResultStatus resultStatus, T data) {
        if (resultStatus == null) {
            return success(data);
        }
        return new Result<T>(resultStatus, data);
    }

    /**
     * 业务成功返回业务代码,描述和返回的参数
     */
    public static <T> Result<T> success(Integer code, String message, T data) {
        return new Result<T>(code, message, data);
    }

    /**
     * 业务异常返回业务代码,描述
     */
    public static <T> Result<T> fail(IResultStatus resultStatus) {
        return new Result<T>(resultStatus.code(), resultStatus.msg(), null);
    }

    /**
     * 业务异常返回业务代码,描述和返回的参数
     */
    public static <T> Result<T> fail(IResultStatus resultStatus, T data) {
        return new Result<T>(resultStatus.code(), resultStatus.msg(), data);
    }

    /**
     * 业务异常返回业务代码,描述和返回的参数
     */
    public static <T> Result<T> fail(Integer code, String message, T data) {
        return new Result<T>(code, message, data);
    }

    /**
     * 业务异常返回业务代码,描述
     */
    public static <T> Result<T> fail(String message) {
        return new Result<T>(ResultStatus.FAIL.code(), message, null);
    }

}