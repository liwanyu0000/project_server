package top.liwanyu.project_server.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.netty.util.internal.StringUtil;
import top.liwanyu.project_server.aspect.GlobalException;
import top.liwanyu.project_server.constant.enums.ResultStatus;
import top.liwanyu.project_server.mapper.CommunicateMapper;
import top.liwanyu.project_server.mapper.UserMapper;
import top.liwanyu.project_server.model.dto.BaseUserDto;
import top.liwanyu.project_server.model.dto.CommunicateDto;
import top.liwanyu.project_server.model.entity.CommunicateEntity;
import top.liwanyu.project_server.model.entity.UserEntity;
import top.liwanyu.project_server.model.param.CommunicateParam;
import top.liwanyu.project_server.model.query.CommunicateQuery;
import top.liwanyu.project_server.service.intf.CommunicateIntf;
import top.liwanyu.project_server.service.intf.NotifyIntf;
import top.liwanyu.project_server.utils.BeanCopyUtils;
import top.liwanyu.project_server.utils.DateUtils;

@Component
public class CommunicateImpl implements CommunicateIntf {

    @Autowired
    private CommunicateMapper communicateMapper;

    @Autowired
    UserMapper userMapper;

    @Autowired
    private NotifyIntf notifyIntf;

    @Override
    public boolean addCommunicate(Integer userIdOne, CommunicateParam communicateParam) {
        if (communicateParam.getUserIdTwo() == null) {
            throw new GlobalException(ResultStatus.COMMUNICATE_USERID_TWO_NOT_NULL);
        }
        if (communicateParam.getUserIdTwo() == userIdOne) {
            throw new GlobalException(ResultStatus.COMMUNICATE_USERID_TWO_NOT_SELF);
        }
        if (StringUtil.isNullOrEmpty(communicateParam.getContent())) {
            throw new GlobalException(ResultStatus.COMMUNICATE_CONTENT_NOT_NULL);
        }
        CommunicateQuery communicateQuery = BeanCopyUtils.copyBean(communicateParam, CommunicateQuery.class);
        communicateQuery.setUserIdOne(userIdOne);
        CommunicateEntity communicateEntity = communicateMapper.getCanAddCommunicate(communicateQuery);
        // send message
        notifyIntf.sendNotify(communicateQuery.getContent(), communicateQuery.getUserIdTwo(), userIdOne);
        if (communicateEntity != null && communicateEntity.getMessageNum() > 0) {
            communicateQuery.setId(communicateEntity.getId());
            communicateQuery.setContent(communicateEntity.getContent() + communicateParam.getContent());
            return communicateMapper.addCommunicate(communicateQuery) > 0;
        } else {
            communicateQuery.setCreateTime(DateUtils.getNowDate());
            return communicateMapper.creatCommunicate(communicateQuery) > 0;
        }
    }

    @Override
    public CommunicateDto getCommunicate(Integer userIdOne, CommunicateParam communicateParam) {
        if (communicateParam.getPageNum() == null) {
            throw new GlobalException(ResultStatus.PAGE_NUM_NOT_NULL);
        }
        if (communicateParam.getUserIdTwo() == null) {
            throw new GlobalException(ResultStatus.COMMUNICATE_USERID_TWO_NOT_NULL);
        }
        CommunicateQuery communicateQuery = BeanCopyUtils.copyBean(communicateParam, CommunicateQuery.class);
        communicateQuery.setUserIdOne(userIdOne);
        communicateQuery.setOffset(communicateParam.getPageNum());
        communicateQuery.setPageSize(1);
        CommunicateEntity communicateEntity = communicateMapper.getCommunicate(communicateQuery);
        if (communicateEntity == null) return null;
        CommunicateDto communicateDto = BeanCopyUtils.copyBean(communicateEntity, CommunicateDto.class);
        communicateDto.setUserModelOne(BeanCopyUtils.copyBean(userMapper.findUserById(communicateEntity.getUserIdOne()), BaseUserDto.class));
        communicateDto.setUserModelTwo(BeanCopyUtils.copyBean(userMapper.findUserById(communicateEntity.getUserIdTwo()), BaseUserDto.class));

        return communicateDto;
    }

    @Override
    public List<BaseUserDto> getCommunicateList(Integer userId) {
        List<Integer> userIdList = communicateMapper.getCommunicateList(userId);
        List<BaseUserDto> userDtoList = new ArrayList<BaseUserDto>();
        for (Integer id : userIdList) {
            UserEntity userEntity = userMapper.findUserById(id);
            if (userEntity == null) {
                continue;
            }
            userDtoList.add(BeanCopyUtils.copyBean(userEntity, BaseUserDto.class));
        }
        return userDtoList;
    }
    
}
