package top.chen.train.member.controller;

import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import top.chen.train.common.context.LoginMemberContext;
import top.chen.train.common.resp.CommonResp;
import top.chen.train.member.req.PassengerQueryReq;
import top.chen.train.member.req.PassengerSaveReq;
import top.chen.train.member.resp.PageResp;
import top.chen.train.member.resp.PassengerQueryResp;
import top.chen.train.member.service.PassengerService;

import java.util.List;

/**
 * @author ChenQi
 * @date 2023/11/21
 * @description: PassengerController
 */
@RestController
@RequestMapping("/passenger")
public class PassengerController {
    @Resource
    private PassengerService passengerService;

    /**
     * 查询 乘车人列表查询接口，只能查看当前成员自己添加的乘客
     * @param req
     * @return
     */
    @GetMapping("/query-list")
    public CommonResp<PageResp<PassengerQueryResp>> queryList(@Valid PassengerQueryReq req) {
        req.setMemberId(LoginMemberContext.getId());
        PageResp<PassengerQueryResp> list = passengerService.queryList(req);
        return new CommonResp<>(list);
    }

    /**
     * 乘车人新增
     * @param req
     * @return
     */
    @PostMapping("/save")
    public CommonResp<Object> save(@Valid @RequestBody PassengerSaveReq req) {
        passengerService.save(req);
        return new CommonResp<>();
    }
}
