package top.chen.train.member.service;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateTime;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import top.chen.train.common.context.LoginMemberContext;
import top.chen.train.common.util.SnowUtil;
import top.chen.train.member.domain.Passenger;
import top.chen.train.member.mapper.PassengerMapper;
import top.chen.train.member.req.PassengerSaveReq;

/**
 * @author ChenQi
 * @date 2023/11/21
 * @description: PassengerService
 */
@Service
public class PassengerService {
    @Resource
    private PassengerMapper passengerMapper;

    /**
     * 乘车人新增
     * @param req
     */
    public void save(PassengerSaveReq req) {
        DateTime now = DateTime.now();
        Passenger passenger = BeanUtil.copyProperties(req, Passenger.class);
        passenger.setMemberId(LoginMemberContext.getId());
        passenger.setId(SnowUtil.getSnowflakeNextId());
        passenger.setCreateTime(now);
        passenger.setUpdateTime(now);
        passengerMapper.insert(passenger);
    }
}
