package top.liwanyu.project_server.model.entity;

import lombok.Data;

@Data
public class TradeEntity {
    // id
    private Integer id;
    // 卖方
    private Integer sellerId;
    // 买方
    private Integer buyerId;
    // 房屋id
    private Integer houseId;
    // 交易信息
    private String info;
    // 创建时间
    private String createTime;
}
