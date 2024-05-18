package top.liwanyu.project_server.model.param;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class HouseParam extends BaseParam{
    // 房屋交易类型(出租或出售)
    private String houseTardeType;
    // 房屋属地
    private String houseTerritory;
    // 地址码
    private String houseAddrCode;
    // 房屋所属用户
    private Integer userId;
    // 房屋状态
    private String houseState;
    // 最大价格
    private Double maxPrice;
    // 最小价格
    private Double minPrice;

    /// 创建或修改时参数
    // 房屋具体地址
    private String houseAddr;
    // 房屋信息(房屋信息有flutter构建，服务器只做存储，不理解具体信息)
    private String houseInfo;
    // 房屋文件
    private String houseFile;
    // 房屋价格
    private Double housePrice;

    private Boolean canPass;
}
