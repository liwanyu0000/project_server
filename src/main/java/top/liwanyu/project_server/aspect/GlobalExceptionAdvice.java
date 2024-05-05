package top.liwanyu.project_server.aspect;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;


@ControllerAdvice
@ResponseBody
public class GlobalExceptionAdvice {

    @SuppressWarnings("rawtypes")
    @ExceptionHandler(value = Exception.class)
    public Result errorHandler(Exception e) {
        if (e instanceof GlobalException) {
            GlobalException globalException = (GlobalException) e;
            return Result.fail(globalException.getResultStatus());
        }
        return Result.fail(e.getMessage());
    }

}
