package top.chen.train.member.req;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author ChenQi
 * @date 2023/11/21
 * @description: PassengerQueryReq
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class PassengerQueryReq extends PageReq{
    private Long memberId;
}
