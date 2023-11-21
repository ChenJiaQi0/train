package top.chen.train.member.controller;

import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.chen.train.common.resp.CommonResp;
import top.chen.train.member.req.PassengerSaveReq;
import top.chen.train.member.service.PassengerService;

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
