package top.chen.train.business.service;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.util.ObjectUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import top.chen.train.common.resp.PageResp;
import top.chen.train.common.util.SnowUtil;
import top.chen.train.business.domain.programmers;
import top.chen.train.business.domain.programmersExample;
import top.chen.train.business.mapper.programmersMapper;
import top.chen.train.business.req.programmersQueryReq;
import top.chen.train.business.req.programmersSaveReq;
import top.chen.train.business.resp.programmersQueryResp;
import jakarta.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class programmersService {

    private static final Logger LOG = LoggerFactory.getLogger(programmersService.class);

    @Resource
    private programmersMapper programmersMapper;

    public void save(programmersSaveReq req) {
        DateTime now = DateTime.now();
        programmers programmers = BeanUtil.copyProperties(req, programmers.class);
        if (ObjectUtil.isNull(programmers.getId())) {
        programmers.setId(SnowUtil.getSnowflakeNextId());
        programmers.setCreateTime(now);
        programmers.setUpdateTime(now);
        programmersMapper.insert(programmers);
        } else {
        programmers.setUpdateTime(now);
        programmersMapper.updateByPrimaryKey(programmers);
        }
    }

    public PageResp<programmersQueryResp> queryList(programmersQueryReq req) {
        programmersExample programmersExample = new programmersExample();
        programmersExample.setOrderByClause("id desc");
        programmersExample.Criteria criteria = programmersExample.createCriteria();

        LOG.info("查询页码：{}", req.getPage());
        LOG.info("每页条数：{}", req.getSize());
        PageHelper.startPage(req.getPage(), req.getSize());
        List<programmers> programmersList = programmersMapper.selectByExample(programmersExample);

        PageInfo<programmers> pageInfo = new PageInfo<>(programmersList);
        LOG.info("总行数：{}", pageInfo.getTotal());
        LOG.info("总页数：{}", pageInfo.getPages());

        List<programmersQueryResp> list = BeanUtil.copyToList(programmersList, programmersQueryResp.class);

        PageResp<programmersQueryResp> pageResp = new PageResp<>();
        pageResp.setTotal(pageInfo.getTotal());
        pageResp.setList(list);
        return pageResp;
    }

        public void delete(Long id) {
            programmersMapper.deleteByPrimaryKey(id);
        }
}