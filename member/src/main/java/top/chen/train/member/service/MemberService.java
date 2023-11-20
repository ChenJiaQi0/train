package top.chen.train.member.service;

import cn.hutool.core.collection.CollUtil;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import top.chen.train.common.exception.BusinessException;
import top.chen.train.common.exception.BusinessExceptionEnum;
import top.chen.train.member.domain.Member;
import top.chen.train.member.domain.MemberExample;
import top.chen.train.member.mapper.MemberMapper;
import top.chen.train.member.req.MemberRegisterReq;

import java.util.List;

/**
 * @author ChenQi
 * @date 2023/11/20
 * @description: MemberService
 */
@Service
public class MemberService {
    @Resource
    private MemberMapper memberMapper;

    public long register(MemberRegisterReq req) {
        String mobile = req.getMobile();
        MemberExample memberExample = new MemberExample();
        memberExample.createCriteria().andMobileEqualTo(mobile);
        List<Member> list = memberMapper.selectByExample(memberExample);

        if (CollUtil.isNotEmpty(list)) {
            throw new BusinessException(BusinessExceptionEnum.MEMBER_MOBILE_EXIST);
        }

        Member member = new Member();
        member.setId(System.currentTimeMillis());
        member.setMobile(mobile);
        memberMapper.insert(member);
        return member.getId();
    }

    public int count() {
        return Math.toIntExact(memberMapper.countByExample(null));
    }
}
