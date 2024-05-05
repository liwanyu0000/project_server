package top.liwanyu.project_server.aspect;

import org.springframework.core.MethodParameter;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import top.liwanyu.project_server.constant.enums.ResultStatus;

@RestControllerAdvice
public class ResponseAdive implements ResponseBodyAdvice<Object> {

    @SuppressWarnings("null")
    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType,
            Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request,
            ServerHttpResponse response) {
        // 防止重复包裹的问题出现
        if (body instanceof Result) {
            return body;
        }
        // String类型不能直接包装，所以要进行些特别的处理

        if (body instanceof String) {
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                return objectMapper.writeValueAsString(Result.success(body));
            } catch (JsonProcessingException e) {
                throw new GlobalException(ResultStatus.JSON_PARSE_ERROR);
            }

        }
        return Result.success(body);
    }

    @SuppressWarnings("null")
    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        // 如果接口返回的类型本身就是Result那就没有必要进行额外的操作，返回false
        if (returnType.getGenericParameterType().equals(Result.class)) {
            return false;
        }
        // 对类或者方法上面注解了@RestController 或者 @ResponseBody 的方法统一处理
        return AnnotatedElementUtils.hasAnnotation(returnType.getContainingClass(), RestController.class) ||
                returnType.hasMethodAnnotation(ResponseBody.class);
    }

}
