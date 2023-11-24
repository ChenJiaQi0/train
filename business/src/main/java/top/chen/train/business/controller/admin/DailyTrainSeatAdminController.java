package top.chen.train.business.controller.admin;

import top.chen.train.common.context.LoginMemberContext;
import top.chen.train.common.resp.CommonResp;
import top.chen.train.common.resp.PageResp;
import top.chen.train.business.req.DailyTrainSeatQueryReq;
import top.chen.train.business.req.DailyTrainSeatSaveReq;
import top.chen.train.business.resp.DailyTrainSeatQueryResp;
import top.chen.train.business.service.DailyTrainSeatService;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/daily-train-seat")
public class DailyTrainSeatAdminController {

@Resource
private DailyTrainSeatService dailyTrainSeatService;

@PostMapping("/save")
public CommonResp<Object> save(@Valid @RequestBody DailyTrainSeatSaveReq req) {
    dailyTrainSeatService.save(req);
    return new CommonResp<>();
    }

    @GetMapping("/query-list")
    public CommonResp<PageResp<DailyTrainSeatQueryResp>> queryList(@Valid DailyTrainSeatQueryReq req) {
        PageResp<DailyTrainSeatQueryResp> list = dailyTrainSeatService.queryList(req);
            return new CommonResp<>(list);
            }

            @DeleteMapping("/delete/{id}")
            public CommonResp<Object> delete(@PathVariable Long id) {
                dailyTrainSeatService.delete(id);
                return new CommonResp<>();
                }

                }