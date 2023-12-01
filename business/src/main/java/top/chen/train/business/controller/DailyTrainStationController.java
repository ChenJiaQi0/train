package top.chen.train.business.controller;

import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.chen.train.business.req.DailyTrainStationQueryAllReq;
import top.chen.train.business.resp.DailyTrainStationQueryResp;
import top.chen.train.business.service.DailyTrainStationService;
import top.chen.train.common.resp.CommonResp;

import java.util.List;

/**
 * @author ChenQi
 * @date 2023/12/1
 * @description: DailyTrainStationController
 */
@RestController
@RequestMapping("/daily-train-station")
public class DailyTrainStationController {
    @Resource
    private DailyTrainStationService dailyTrainStationService;

    @GetMapping("/query-by-train-code")
    public CommonResp<List<DailyTrainStationQueryResp>> queryByTrain(@Valid DailyTrainStationQueryAllReq req) {
        List<DailyTrainStationQueryResp> list = dailyTrainStationService.queryByTrain(req.getDate(), req.getTrainCode());
        return new CommonResp<>(list);
    }
}
