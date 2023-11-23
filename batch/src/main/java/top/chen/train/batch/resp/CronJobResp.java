package top.chen.train.batch.resp;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.Date;

/**
 * @author ChenQi
 * @date 2023/11/23
 * @description: CronJobResp 定时任务响应出参类
 */
@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class CronJobResp {
    private String group;

    private String name;

    private String description;

    private String state;

    private String cronExpression;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date nextFireTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date preFireTime;
}
