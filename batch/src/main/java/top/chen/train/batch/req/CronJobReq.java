package top.chen.train.batch.req;

import lombok.Data;

/**
 * @author ChenQi
 * @date 2023/11/23
 * @description: CronJobReq
 */
@Data
public class CronJobReq {
    private String group;

    private String name;

    private String description;

    private String cronExpression;
}
