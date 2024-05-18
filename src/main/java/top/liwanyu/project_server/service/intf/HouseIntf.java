package top.liwanyu.project_server.service.intf;


import java.util.List;

import top.liwanyu.project_server.model.dto.HouseDto;
import top.liwanyu.project_server.model.param.HouseParam;

public interface HouseIntf {
    public Integer addHouse(HouseParam houseParam);
    public Integer deleteHouse(Integer id, Integer userId);
    public Integer updateHouse(HouseParam houseParam, Integer id);
    public Integer updateHouse(String state, Integer id);
    public HouseDto getHouse(Integer id);
    public List<HouseDto> getHouses(HouseParam houseParam);
    public Integer reviewHouse(Integer id, Boolean canPass);
}
