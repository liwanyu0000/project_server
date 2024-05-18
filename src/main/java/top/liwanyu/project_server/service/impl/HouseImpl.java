package top.liwanyu.project_server.service.impl;

import org.springframework.stereotype.Component;

import io.netty.util.internal.StringUtil;
import jakarta.annotation.Resource;
import top.liwanyu.project_server.aspect.GlobalException;
import top.liwanyu.project_server.constant.consist.Houes;
import top.liwanyu.project_server.constant.enums.ResultStatus;
import top.liwanyu.project_server.mapper.HouseMapper;
import top.liwanyu.project_server.mapper.UserMapper;
import top.liwanyu.project_server.model.dto.BaseUserDto;
import top.liwanyu.project_server.model.dto.HouseDto;
import top.liwanyu.project_server.model.entity.HouseEntity;
import top.liwanyu.project_server.model.entity.UserEntity;
import top.liwanyu.project_server.model.param.HouseParam;
import top.liwanyu.project_server.model.query.HouseQuery;
import top.liwanyu.project_server.service.intf.HouseIntf;
import top.liwanyu.project_server.utils.BeanCopyUtils;
import top.liwanyu.project_server.utils.DateUtils;

import java.util.ArrayList;
import java.util.List;

@Component
public class HouseImpl implements HouseIntf {

    @Resource
    private HouseMapper houseMapper;

    @Resource
    private UserMapper userMapper;

    @Override
    public Integer addHouse(HouseParam houseParam) {
        if (userMapper.findUserById(houseParam.getUserId()) == null){
            throw new GlobalException(ResultStatus.USER_NOT_FOUND);
        }
        if (StringUtil.isNullOrEmpty(houseParam.getHouseTardeType())) {
            throw new GlobalException(ResultStatus.HOUSE_TRADE_TYPE_NOT_NULL);
        }
        if (StringUtil.isNullOrEmpty(houseParam.getHouseTerritory())) {
            throw new GlobalException(ResultStatus.HOUSE_TERRITORY_NOT_NULL);
        }
        if (houseParam.getHousePrice() == null) {
            throw new GlobalException(ResultStatus.HOUSE_PRICE_NOT_NULL);
        }
        if (StringUtil.isNullOrEmpty(houseParam.getHouseAddrCode())) {
            throw new GlobalException(ResultStatus.HOUSE_ADDRCODE_NOT_NULL);
        }
        if (StringUtil.isNullOrEmpty(houseParam.getHouseAddr())) {
            throw new GlobalException(ResultStatus.HOUSE_ADDR_NOT_NULL);
        }
        if (StringUtil.isNullOrEmpty(houseParam.getHouseInfo())) {
            houseParam.setHouseInfo("{}");
        }
        if (StringUtil.isNullOrEmpty(houseParam.getHouseFile())) {
            houseParam.setHouseFile("");
        }
        HouseQuery houseQuery = BeanCopyUtils.copyBean(houseParam, HouseQuery.class);
        houseQuery.setHouseState(Houes.HOUSE_STATUS_AUDIT);
        houseQuery.setCreateTime(DateUtils.getNowDate());
        houseQuery.setUpdateTime(DateUtils.getNowDate());
        return houseMapper.addHouse(houseQuery);
    }

    @Override
    public Integer deleteHouse(Integer id, Integer userId) {
        HouseEntity HouseEntity = houseMapper.getHouse(id);
        if (HouseEntity == null) {
            throw new GlobalException(ResultStatus.NOT_EXISTS_HOUSE);
        }
        if (HouseEntity.getUserId() != userId) {
            throw new GlobalException(ResultStatus.NOT_UPDATE_OTHER_HOUSE);
        }
        return houseMapper.deleteHouse(id, DateUtils.getNowDate());
    }

    @Override
    public Integer updateHouse(HouseParam houseParam, Integer id) {
        Integer userId = houseParam.getUserId();
        if (userId == null) {
            throw new GlobalException(ResultStatus.USER_ID_NOT_NULL);
        }
        HouseEntity HouseEntity = houseMapper.getHouse(id);
        if (HouseEntity == null) {
            throw new GlobalException(ResultStatus.NOT_EXISTS_HOUSE);
        }
        if (HouseEntity.getUserId() != userId) {
            throw new GlobalException(ResultStatus.NOT_UPDATE_OTHER_HOUSE);
        }
        houseParam.setUserId(null);
        HouseQuery HouseQuery = BeanCopyUtils.copyBean(houseParam, HouseQuery.class);
        HouseQuery.setId(id);
        HouseQuery.setHouseState(Houes.HOUSE_STATUS_AUDIT);
        HouseQuery.setUpdateTime(DateUtils.getNowDate());
        return houseMapper.updateHouse(HouseQuery);
    }

    @Override
    public Integer updateHouse(String state, Integer id) {
        if (StringUtil.isNullOrEmpty(state)) {
            throw new GlobalException(ResultStatus.REQUEST_PARAM_ERROR);
        }
        HouseQuery houseQuery = new HouseQuery();
        houseQuery.setId(id);
        houseQuery.setHouseState(state);
        houseQuery.setUpdateTime(DateUtils.getNowDate());
        return houseMapper.updateHouse(houseQuery);
    }



    private HouseDto fromEntity(HouseEntity HouseEntity) {
        HouseDto houseDto = BeanCopyUtils.copyBean(HouseEntity, HouseDto.class);
        UserEntity userEntity = userMapper.findUserById(HouseEntity.getUserId());
        BaseUserDto baseUserDto = BeanCopyUtils.copyBean(userEntity, BaseUserDto.class);
        houseDto.setHouseOwner(baseUserDto);
        return houseDto;
    }

    @Override
    public HouseDto getHouse(Integer id) {
        HouseEntity houseEntity = houseMapper.getHouse(id);
        if (houseEntity == null) {
            throw new GlobalException(ResultStatus.NOT_EXISTS_HOUSE);
        }
        return fromEntity(houseEntity);
    }

    @Override
    public List<HouseDto> getHouses(HouseParam houseParam) {
        HouseQuery houseQuery = BeanCopyUtils.copyBean(houseParam, HouseQuery.class);
        if (houseParam.getPageSize() != null && houseParam.getPageNum() != null)  {
            houseQuery.setOffset(houseParam.getPageSize() * houseParam.getPageNum() );
            houseQuery.setPageSize(houseParam.getPageSize());
        } else {
            houseQuery.setOffset(0);
            houseQuery.setPageSize(100);
        }
        List<HouseEntity> houseEntities = houseMapper.getHouses(houseQuery);
        List<HouseDto> houseDtos = new ArrayList<>();

        for (HouseEntity houseEntity : houseEntities) {
            if (houseEntity == null) continue;
            houseDtos.add(fromEntity(houseEntity));
        }
        return houseDtos;
    }

    @Override
    public Integer reviewHouse(Integer id, Boolean canPass) {
        if (id == null) {
            throw new GlobalException(ResultStatus.REQUEST_PARAM_ERROR);
        }
        if (canPass == null) {
            throw new GlobalException(ResultStatus.REQUEST_PARAM_ERROR);
        }
        return houseMapper.reviewHouse(id, canPass ? Houes.HOUSE_STATUS_PUBLISH : Houes.HOUSE_STATUS_NOT_PASS, DateUtils.getNowDate());
    }

}
