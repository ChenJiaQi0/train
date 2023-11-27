package top.chen.train.business.mapper;

import java.util.Date;

/**
 * @author ChenQi
 * @date 2023/11/27
 * @description: DailyTrainTicketMapperCust
 */
public interface DailyTrainTicketMapperCust {
    void updateCountBySell(Date date
            , String trainCode
            , String seatTypeCode
            , Integer minStartIndex
            , Integer maxStartIndex
            , Integer minEndIndex
            , Integer maxEndIndex);
}
