package top.liwanyu.project_server.model.dto;

import lombok.Data;

@Data
public class HouseDto {
    // id
    private Integer id;
    // 房屋交易类型(出租或出售)
    private String houseTardeType;
    // 房屋属地
    private String houseTerritory;
    // 地址码
    private String houseAddrCode;
    // 房屋具体地址
    private String houseAddr;
    // 房屋信息(房屋信息有flutter构建，服务器只做存储，不理解具体信息)
    private String houseInfo;
    // 房屋文件
    private String houseFile;
    // 房屋价格
    private Double housePrice;
    // 房屋所属用户
    private BaseUserDto houseOwner;
    // 房屋状态
    private String houseState;
    // 创建时间
    private String createTime;
    // 更新时间
    private String updateTime;
}
