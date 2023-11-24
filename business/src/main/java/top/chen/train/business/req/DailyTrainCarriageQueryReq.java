package top.chen.train.business.req;

import org.springframework.format.annotation.DateTimeFormat;
import top.chen.train.common.req.PageReq;

import java.util.Date;

public class DailyTrainCarriageQueryReq extends PageReq {
    private String trainCode;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date;

    public String getTrainCode() {
        return trainCode;
    }

    public void setTrainCode(String trainCode) {
        this.trainCode = trainCode;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "DailyTrainCarriageQueryReq{" +
        "} " + super.toString();
    }
}