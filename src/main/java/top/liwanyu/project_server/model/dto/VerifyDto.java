package top.liwanyu.project_server.model.dto;

import lombok.Data;

@Data
public class VerifyDto {
    // 验证码唯一标识
    private String codeToken;
    // 验证码图片
    private String codeImg;
    
}
