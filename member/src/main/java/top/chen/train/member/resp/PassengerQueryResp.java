package top.chen.train.member.resp;

import lombok.Data;

import java.util.Date;

/**
 * @author ChenQi
 * @date 2023/11/21
 * @description: PassengerQueryResp
 */
@Data
public class PassengerQueryResp {
    private Long id;
    private Long memberId;
    private String name;
    private String idCard;
    private String type;
    private Date createTime;
    private Date updateTime;
}
