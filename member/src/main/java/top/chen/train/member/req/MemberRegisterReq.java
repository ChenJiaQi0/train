package top.chen.train.member.req;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author ChenQi
 * @date 2023/11/20
 * @description: MemberRegisterReq
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MemberRegisterReq {
    private String mobile;
}
