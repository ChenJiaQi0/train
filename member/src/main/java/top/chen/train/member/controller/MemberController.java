package top.chen.train.member.controller;

import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.chen.train.common.resp.CommonResp;
import top.chen.train.member.req.MemberRegisterReq;
import top.chen.train.member.req.MemberSendCodeReq;
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

    @PostMapping("/send-code")
    public CommonResp<Long> sendCode(@Valid MemberSendCodeReq req) {
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
