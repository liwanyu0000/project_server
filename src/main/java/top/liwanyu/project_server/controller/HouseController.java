package top.liwanyu.project_server.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import top.liwanyu.project_server.aspect.GlobalException;
import top.liwanyu.project_server.model.dto.HouseDto;
import top.liwanyu.project_server.model.param.HouseParam;
import top.liwanyu.project_server.service.intf.HouseIntf;
import top.liwanyu.project_server.utils.TokenUtils;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/house")
public class HouseController {

    @Resource
    private HouseIntf houseIntf;

    @Resource
    private TokenUtils tokenUtils;

    @PostMapping("/addHouse")
    public Integer addHouse(@RequestBody HouseParam houseParm, HttpServletRequest request) {
        int userId = tokenUtils.getId(request.getHeader("token"));
        houseParm.setUserId(userId);
        return houseIntf.addHouse(houseParm);
    }

    @DeleteMapping("/deleteHouse/{id}")
    public Integer deleteHouse(@PathVariable Integer id, HttpServletRequest request){
        int userId = tokenUtils.getId(request.getHeader("token"));
        return houseIntf.deleteHouse(id, userId);
    }

    @PostMapping("/updateHouse/{id}")
    public Integer updateHouse(@PathVariable Integer id, @RequestBody HouseParam houseParam, HttpServletRequest request) {
        int userId = tokenUtils.getId(request.getHeader("token"));
        houseParam.setUserId(userId);
        return houseIntf.updateHouse(houseParam, id);
    }

    @PostMapping("/changeHouseState/{id}")
    public Integer changeHouseState(@PathVariable Integer id, @RequestBody String state, HttpServletRequest request) {
        tokenUtils.verifyAdmin(request.getHeader("token"));
        return houseIntf.updateHouse(state, id);
    }

    @GetMapping("/getHouse/{id}")
    public HouseDto getHouse(@PathVariable Integer id){
        return houseIntf.getHouse(id);
    }

    @PostMapping("/getHouses")
    public List<HouseDto> getHouses(@RequestBody HouseParam houseParam) {
        return houseIntf.getHouses(houseParam);
    }

    @PostMapping("/reviewHouse/{id}")
    public Integer reviewHouse(@PathVariable Integer id, @RequestBody  HouseParam houseParam, HttpServletRequest request) {
        tokenUtils.verifyAdmin(request.getHeader("token"));
        if (houseParam.getCanPass() == null) throw new GlobalException("参数错误");
        return houseIntf.reviewHouse(id, houseParam.getCanPass());
        
    }

    @PostMapping("/getHousesForToken")
    public List<HouseDto> getHousesForToken(@RequestBody HouseParam houseParam, HttpServletRequest request) {
        int userId = tokenUtils.getId(request.getHeader("token"));
        houseParam.setUserId(userId);
        return houseIntf.getHouses(houseParam);
    }
    
}
