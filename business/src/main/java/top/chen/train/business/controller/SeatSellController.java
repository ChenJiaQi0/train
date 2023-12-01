package top.chen.train.business.controller;

import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.chen.train.business.req.SeatSellReq;
import top.chen.train.business.resp.SeatSellResp;
import top.chen.train.business.service.DailyTrainSeatService;
import top.chen.train.common.resp.CommonResp;

import java.util.List;

/**
 * @author ChenQi
 * @date 2023/12/1
 * @description: SeatSellController
 */
@RestController
@RequestMapping("/seat-sell")
public class SeatSellController {
    @Resource
    private DailyTrainSeatService dailyTrainSeatService;

    @GetMapping("/query")
    public CommonResp<List<SeatSellResp>> query(@Valid SeatSellReq req) {
        List<SeatSellResp> seatList = dailyTrainSeatService.querySeatSell(req);
        return new CommonResp<>(seatList);
    }
}
