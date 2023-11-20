package top.chen.train.member.service;

import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import top.chen.train.member.mapper.MemberMapper;

/**
 * @author ChenQi
 * @date 2023/11/20
 * @description: MemberService
 */
@Service
public class MemberService {
    @Resource
    private MemberMapper memberMapper;

    public int count() {
        return Math.toIntExact(memberMapper.countByExample(null));
    }
}
