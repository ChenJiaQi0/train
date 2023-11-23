package top.chen.train.batch.job;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * @author ChenQi
 * @date 2023/11/23
 * @description: Test2Job
 */
@DisallowConcurrentExecution
public class Test2Job implements Job {
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println("TestJob222222 TEST开始");
        System.out.println("TestJob222222 TEST结束");
    }
}
