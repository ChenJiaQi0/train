package top.chen.train.common.context;

import lombok.extern.slf4j.Slf4j;
import top.chen.train.common.resp.MemberLoginResp;

/**
 * @author ChenQi
 * @date 2023/11/21
 * @description: LoginMemberContext
 */
@Slf4j
public class LoginMemberContext {
    private static final ThreadLocal<MemberLoginResp> MEMBER = new ThreadLocal<>();

    public static MemberLoginResp getMember() {
        return MEMBER.get();
    }

    public static void setMember(MemberLoginResp member) {
        LoginMemberContext.MEMBER.set(member);
    }

    public static Long getId() {
        try {
            return MEMBER.get().getId();
        } catch (Exception e) {
            log.error("获取登录会员信息异常", e);
            throw e;
        }
    }
}
