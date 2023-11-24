package top.chen.train.batch.job;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import jakarta.annotation.Resource;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import top.chen.train.batch.feign.BusinessFeign;
import top.chen.train.common.resp.CommonResp;

import java.util.Date;

/**
 * @author ChenQi
 * @date 2023/11/24
 * @description: DailyTrainJob 每日车次定时任务
 */
@DisallowConcurrentExecution
public class DailyTrainJob implements Job {
    public static final Logger LOG = LoggerFactory.getLogger(DailyTrainJob.class);
    @Resource
    private BusinessFeign businessFeign;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        LOG.info("生成5天后的车次定时任务开始");
        Date today = new Date();
        DateTime dayTime = DateUtil.offsetDay(today, 5);
        Date offsetDay = dayTime.toJdkDate();
        CommonResp<Object> commonResp = businessFeign.genDaily(offsetDay);
        LOG.info("commonResp：{}", commonResp);
        LOG.info("生成5天后的车次定时任务结束");
    }
}
