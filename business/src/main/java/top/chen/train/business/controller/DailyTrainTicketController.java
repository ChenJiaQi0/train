package top.chen.train.business.controller;

import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.chen.train.business.req.DailyTrainTicketQueryReq;
import top.chen.train.business.resp.DailyTrainTicketQueryResp;
import top.chen.train.business.service.DailyTrainTicketService;
import top.chen.train.common.resp.CommonResp;
import top.chen.train.common.resp.PageResp;

import java.util.List;

/**
 * @author ChenQi
 * @date 2023/11/26
 * @description: DailyTrainTicketController
 */
@RestController
@RequestMapping("/daily-train-ticket")
public class DailyTrainTicketController {
    @Resource
    private DailyTrainTicketService dailyTrainTicketService;

    @GetMapping("/query-list")
    public CommonResp<PageResp<DailyTrainTicketQueryResp>> queryList(@Valid DailyTrainTicketQueryReq req){
        PageResp<DailyTrainTicketQueryResp> list = dailyTrainTicketService.queryList(req);
        return new CommonResp<>(list);
    }
}
