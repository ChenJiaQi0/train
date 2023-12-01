package top.chen.train.business.req;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author ChenQi
 * @date 2023/12/1
 * @description: SeatSellReq
 */
@Data
public class SeatSellReq {
    /**
     * 日期
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "【日期】不能为空")
    private Date date;

    /**
     * 车次编号
     */
    @NotNull(message = "【车次编号】不能为空")
    private String trainCode;
}
