package top.chen.train.member.resp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author ChenQi
 * @date 2023/11/20
 * @description: MemberLoginResp
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MemberLoginResp {
    private Long id;
    private String mobile;
    private String token;
}
