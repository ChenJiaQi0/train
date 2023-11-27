package top.chen.train.common.req;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Date;

/**
 * @author ChenQi
 * @date 2023/11/27
 * @description: MemberTicketReq
 */
@Data
public class MemberTicketReq {
    /**
     * 乘客id
     */
    @NotNull(message = "【会员id】不能为空")
    private Long memberId;

    /**
     * 乘客id
     */
    @NotNull(message = "【乘客id】不能为空")
    private Long passengerId;

    /**
     * 乘客id
     */
    @NotNull(message = "【乘客名字】不能为空")
    private String passengerName;

    /**
     * 日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @NotNull(message = "【日期】不能为空")
    private Date date;

    /**
     * 车次编号
     */
    @NotBlank(message = "【车次编号】不能为空")
    private String trainCode;

    /**
     * 箱序
     */
    @NotNull(message = "【箱序】不能为空")
    private Integer carriageIndex;

    /**
     * 排号|01, 02
     */
    @NotBlank(message = "【排号】不能为空")
    private String row;

    /**
     * 列号|枚举[SeatColumnEnum]
     */
    @NotBlank(message = "【列号】不能为空")
    private String col;

    /**
     * 出发站
     */
    @NotBlank(message = "【出发站】不能为空")
    private String start;

    /**
     * 出发时间
     */
    @JsonFormat(pattern = "HH:mm:ss", timezone = "GMT+8")
    @NotNull(message = "【出发时间】不能为空")
    private Date startTime;

    /**
     * 到达站
     */
    @NotBlank(message = "【到达站】不能为空")
    private String end;

    /**
     * 到站时间
     */
    @JsonFormat(pattern = "HH:mm:ss", timezone = "GMT+8")
    @NotNull(message = "【到站时间】不能为空")
    private Date endTime;

    /**
     * 座位类型|枚举[SeatTypeEnum]
     */
    @NotBlank(message = "【座位类型】不能为空")
    private String seatType;
}
