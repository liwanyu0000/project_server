package top.liwanyu.project_server.model.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class LoginUserDto extends UserDto {
    
    // token
    private String token;
}
