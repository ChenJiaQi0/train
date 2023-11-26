package top.chen.train.business.controller.admin;

import top.chen.train.common.context.LoginMemberContext;
import top.chen.train.common.resp.CommonResp;
import top.chen.train.common.resp.PageResp;
import top.chen.train.business.req.DailyTrainTicketQueryReq;
import top.chen.train.business.req.DailyTrainTicketSaveReq;
import top.chen.train.business.resp.DailyTrainTicketQueryResp;
import top.chen.train.business.service.DailyTrainTicketService;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/daily-train-ticket")
public class DailyTrainTicketAdminController {

@Resource
private DailyTrainTicketService dailyTrainTicketService;

@PostMapping("/save")
public CommonResp<Object> save(@Valid @RequestBody DailyTrainTicketSaveReq req) {
    dailyTrainTicketService.save(req);
    return new CommonResp<>();
    }

    @GetMapping("/query-list")
    public CommonResp<PageResp<DailyTrainTicketQueryResp>> queryList(@Valid DailyTrainTicketQueryReq req) {
        PageResp<DailyTrainTicketQueryResp> list = dailyTrainTicketService.queryList(req);
            return new CommonResp<>(list);
            }

            @DeleteMapping("/delete/{id}")
            public CommonResp<Object> delete(@PathVariable Long id) {
                dailyTrainTicketService.delete(id);
                return new CommonResp<>();
                }

                }