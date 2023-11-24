package top.chen.train.business.service;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjectUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import top.chen.train.business.domain.DailyTrainSeatExample;
import top.chen.train.business.domain.Train;
import top.chen.train.common.resp.PageResp;
import top.chen.train.common.util.SnowUtil;
import top.chen.train.business.domain.DailyTrain;
import top.chen.train.business.domain.DailyTrainExample;
import top.chen.train.business.mapper.DailyTrainMapper;
import top.chen.train.business.req.DailyTrainQueryReq;
import top.chen.train.business.req.DailyTrainSaveReq;
import top.chen.train.business.resp.DailyTrainQueryResp;
import jakarta.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class DailyTrainService {
    @Resource
    private TrainService trainService;

    private static final Logger LOG = LoggerFactory.getLogger(DailyTrainService.class);

    @Resource
    private DailyTrainMapper dailyTrainMapper;
    @Resource
    private DailyTrainStationService dailyTrainStationService;

    public void save(DailyTrainSaveReq req) {
        DateTime now = DateTime.now();
        DailyTrain dailyTrain = BeanUtil.copyProperties(req, DailyTrain.class);
        if (ObjectUtil.isNull(dailyTrain.getId())) {
        dailyTrain.setId(SnowUtil.getSnowflakeNextId());
        dailyTrain.setCreateTime(now);
        dailyTrain.setUpdateTime(now);
        dailyTrainMapper.insert(dailyTrain);
        } else {
        dailyTrain.setUpdateTime(now);
        dailyTrainMapper.updateByPrimaryKey(dailyTrain);
        }
    }

    public PageResp<DailyTrainQueryResp> queryList(DailyTrainQueryReq req) {
        DailyTrainExample dailyTrainExample = new DailyTrainExample();
        dailyTrainExample.setOrderByClause("date desc, code asc");
        DailyTrainExample.Criteria criteria = dailyTrainExample.createCriteria();
        if (ObjectUtil.isNotNull(req.getDate())){
            criteria.andDateEqualTo(req.getDate());
        }
        if (ObjectUtil.isNotEmpty(req.getCode())) {
            criteria.andCodeEqualTo(req.getCode());
        }

        LOG.info("查询页码：{}", req.getPage());
        LOG.info("每页条数：{}", req.getSize());
        PageHelper.startPage(req.getPage(), req.getSize());
        List<DailyTrain> dailyTrainList = dailyTrainMapper.selectByExample(dailyTrainExample);

        PageInfo<DailyTrain> pageInfo = new PageInfo<>(dailyTrainList);
        LOG.info("总行数：{}", pageInfo.getTotal());
        LOG.info("总页数：{}", pageInfo.getPages());

        List<DailyTrainQueryResp> list = BeanUtil.copyToList(dailyTrainList, DailyTrainQueryResp.class);

        PageResp<DailyTrainQueryResp> pageResp = new PageResp<>();
        pageResp.setTotal(pageInfo.getTotal());
        pageResp.setList(list);
        return pageResp;
    }

        public void delete(Long id) {
            dailyTrainMapper.deleteByPrimaryKey(id);
        }

    /**
     * 生成某日的所有车次信息
     * @param date
     */
    public void genDaily(Date date) {
        List<Train> trainList = trainService.selectAll();
        if (CollUtil.isEmpty(trainList)) {
            LOG.info("没有车次的基础数据，任务结束");
        }
        for (Train train : trainList) {
            genDailyTrain(date, train);
        }
    }

    private void genDailyTrain(Date date, Train train) {
        // 1.删除该车次已有数据
        DailyTrainExample DailyTrainExample = new DailyTrainExample();
        DailyTrainExample.createCriteria()
                .andDateEqualTo(date)
                .andCodeEqualTo(train.getCode());
        dailyTrainMapper.deleteByExample(DailyTrainExample);

        // 2.生成该车次数据
        DateTime now = DateTime.now();
        // 属性拷贝
        DailyTrain dailyTrain = BeanUtil.copyProperties(train, DailyTrain.class);
        // 补全或修改其他属性
        dailyTrain.setId(SnowUtil.getSnowflakeNextId());
        dailyTrain.setDate(date);
        dailyTrain.setCreateTime(now);
        dailyTrain.setUpdateTime(now);
        dailyTrainMapper.insert(dailyTrain);

        // 生成该车次的车站数据
        dailyTrainStationService.genDaily(date, train.getCode());
        LOG.info("生成日期：【{}】车次【{}】的车站信息结束", DateUtil.formatDate(date), train.getCode());
    }
}