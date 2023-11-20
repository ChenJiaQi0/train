package top.chen.train.member.controller;

import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import top.chen.train.common.resp.CommonResp;
import top.chen.train.member.req.MemberLoginReq;
import top.chen.train.member.req.MemberRegisterReq;
import top.chen.train.member.req.MemberSendCodeReq;
import top.chen.train.member.resp.MemberLoginResp;
import top.chen.train.member.service.MemberService;

/**
 * @author ChenQi
 * @date 2023/11/20
 * @description: MemberController
 */
@RestController
@RequestMapping("/member")
public class MemberController {
    @Resource
    private MemberService memberService;

    /**
     * 登录
     * @param req
     * @return
     */
    @PostMapping("/login")
    public CommonResp<MemberLoginResp> login(@Valid MemberLoginReq req) {
        MemberLoginResp resp = memberService.login(req);
        return new CommonResp<>(resp);
    }

    /**
     * 发送验证码
     * @param req
     * @return
     */
    @PostMapping("/send-code")
    public CommonResp<Long> sendCode(@Valid @RequestBody MemberSendCodeReq req) {
        memberService.sendCode(req);
        return new CommonResp<>();
    }

    @PostMapping("/register")
    public CommonResp<Long> register(@Valid MemberRegisterReq req) {
        long register = memberService.register(req);
        return new CommonResp<>(register);
    }

    @GetMapping("/count")
    public Integer count() {
        return memberService.count();
    }
}
