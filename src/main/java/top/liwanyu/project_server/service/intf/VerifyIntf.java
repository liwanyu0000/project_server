package top.liwanyu.project_server.service.intf;


import top.liwanyu.project_server.model.dto.VerifyDto;

public interface VerifyIntf {
    public VerifyDto getVerifyCode();

    public void checkVerifyCode(String code, String codeToken);
}