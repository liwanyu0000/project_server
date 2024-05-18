package top.liwanyu.project_server.mapper;

import java.util.List;

import top.liwanyu.project_server.model.entity.HouseEntity;
import top.liwanyu.project_server.model.query.HouseQuery;

public interface HouseMapper {
    public Integer addHouse(HouseQuery houseQuery);

    public Integer deleteHouse(Integer id, String updateTime);

    public Integer updateHouse(HouseQuery houseQuery);

    public HouseEntity getHouse(Integer id);

    public List<HouseEntity> getHouses(HouseQuery houseQuery);

    public Integer reviewHouse(Integer id, String houseState, String updateTime);


}
