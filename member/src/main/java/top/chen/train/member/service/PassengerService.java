package top.chen.train.member.service;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.util.ObjectUtil;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import top.chen.train.common.context.LoginMemberContext;
import top.chen.train.common.util.SnowUtil;
import top.chen.train.member.domain.Passenger;
import top.chen.train.member.domain.PassengerExample;
import top.chen.train.member.mapper.PassengerMapper;
import top.chen.train.member.req.PassengerQueryReq;
import top.chen.train.member.req.PassengerSaveReq;
import top.chen.train.member.resp.PassengerQueryResp;

import java.util.List;

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
     * 查询 乘车人列表查询接口，只能查看当前成员自己添加的乘客
     * @param req
     * @return
     */
    public List<PassengerQueryResp> queryList(PassengerQueryReq req) {
        PassengerExample passengerExample = new PassengerExample();
        PassengerExample.Criteria criteria = passengerExample.createCriteria();
        if (ObjectUtil.isNotNull(req.getMemberId())) {
            criteria.andMemberIdEqualTo(req.getMemberId());
        }
        List<Passenger> passengerList = passengerMapper.selectByExample(passengerExample);
        return BeanUtil.copyToList(passengerList, PassengerQueryResp.class);
    }

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
