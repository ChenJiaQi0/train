package top.chen.train.member.service;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.util.ObjectUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import top.chen.train.common.context.LoginMemberContext;
import top.chen.train.common.util.SnowUtil;
import top.chen.train.member.domain.Passenger;
import top.chen.train.member.domain.PassengerExample;
import top.chen.train.member.mapper.PassengerMapper;
import top.chen.train.member.req.PassengerQueryReq;
import top.chen.train.member.req.PassengerSaveReq1;
import top.chen.train.member.resp.PageResp;
import top.chen.train.member.resp.PassengerQueryResp1;

import java.util.List;

/**
 * @author ChenQi
 * @date 2023/11/21
 * @description: PassengerService
 */
@Service
@Slf4j
public class PassengerService1 {
    @Resource
    private PassengerMapper passengerMapper;

    /**
     * 乘车人删除
     * @param id
     */
    public void delete(Long id) {
        passengerMapper.deleteByPrimaryKey(id);
    }

    /**
     * 查询 乘车人列表查询接口，只能查看当前成员自己添加的乘客
     * @param req
     * @return
     */
    public PageResp<PassengerQueryResp1> queryList(PassengerQueryReq req) {
        PassengerExample passengerExample = new PassengerExample();
        passengerExample.setOrderByClause("id desc");
        PassengerExample.Criteria criteria = passengerExample.createCriteria();
        if (ObjectUtil.isNotNull(req.getMemberId())) {
            criteria.andMemberIdEqualTo(req.getMemberId());
        }

        log.info("查询页码：{}", req.getPage());
        log.info("每页条数：{}", req.getSize());
        PageHelper.startPage(req.getPage(),req.getSize());
        List<Passenger> passengerList = passengerMapper.selectByExample(passengerExample);

        PageInfo<Passenger> pageInfo = new PageInfo<>(passengerList);
        log.info("总行数：{}", pageInfo.getTotal());
        log.info("总页数：{}", pageInfo.getPages());

        List<PassengerQueryResp1> list = BeanUtil.copyToList(passengerList, PassengerQueryResp1.class);

        PageResp<PassengerQueryResp1> pageResp = new PageResp<>();
        pageResp.setTotal(pageInfo.getTotal());
        pageResp.setList(list);
        return pageResp;
    }

    /**
     * 乘车人新增与编辑
     * @param req
     */
    public void save(PassengerSaveReq1 req) {
        DateTime now = DateTime.now();
        Passenger passenger = BeanUtil.copyProperties(req, Passenger.class);
        if (ObjectUtil.isNull(passenger.getId())) {
            passenger.setMemberId(LoginMemberContext.getId());
            passenger.setId(SnowUtil.getSnowflakeNextId());
            passenger.setCreateTime(now);
            passenger.setUpdateTime(now);
            passengerMapper.insert(passenger);
        } else {
         passenger.setUpdateTime(now);
         passengerMapper.updateByPrimaryKey(passenger);
        }
    }
}
