package top.chen.train.business.resp;

import lombok.Data;

/**
 * @author ChenQi
 * @date 2023/12/1
 * @description: SeatSellResp
 */
@Data
public class SeatSellResp {
    /**
     * 厢序
     */
    private Integer carriageIndex;

    /**
     * 排号|01, 02
     */
    private String row;

    /**
     * 列号|枚举[SeatColEnum]
     */
    private String col;

    /**
     * 座位类型|枚举[SeatTypeEnum]
     */
    private String seatType;

    /**
     * 售卖情况|将经过的车站用01拼接，0表示可卖，1表示已卖
     */
    private String sell;
}
