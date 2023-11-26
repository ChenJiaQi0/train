package top.chen.train.business.controller;

import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.chen.train.business.resp.StationQueryResp;
import top.chen.train.business.service.StationService;
import top.chen.train.common.resp.CommonResp;

import java.util.List;

/**
 * @author ChenQi
 * @date 2023/11/26
 * @description: StationController
 */
@RestController
@RequestMapping("/station")
public class StationController {
    @Resource
    private StationService stationService;

    @GetMapping("/query-all")
    public CommonResp<List<StationQueryResp>> queryList(){
        List<StationQueryResp> list = stationService.queryAll();
        return new CommonResp<>(list);
    }
}
