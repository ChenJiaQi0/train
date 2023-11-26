package top.chen.train.business.controller;

import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.chen.train.business.resp.TrainQueryResp;
import top.chen.train.business.service.TrainService;
import top.chen.train.common.resp.CommonResp;

import java.util.List;

/**
 * @author ChenQi
 * @date 2023/11/26
 * @description: TrainController
 */
@RestController
@RequestMapping("/train")
public class TrainController {
    @Resource
    private TrainService trainService;

    @GetMapping("/query-all")
    public CommonResp<List<TrainQueryResp>> queryList() {
        List<TrainQueryResp> list = trainService.queryAll();
        return new CommonResp<>(list);
    }
}
