package top.chen.train.business.req;

import top.chen.train.common.req.PageReq;

public class StudentQueryReq extends PageReq {
    private String school;

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    @Override
    public String toString() {
        return "StudentQueryReq{" +
        "} " + super.toString();
    }
}