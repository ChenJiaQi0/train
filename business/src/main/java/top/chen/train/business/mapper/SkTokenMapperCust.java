package top.chen.train.business.mapper;

import java.util.Date;

/**
 * @author ChenQi
 * @date 2023/12/1
 * @description: SkTokenMapperCust
 */
public interface SkTokenMapperCust {
    int decrease(Date date, String trainCode);
}
