package top.chen.train.common.exception;

import lombok.Getter;
import lombok.ToString;

/**
 * @author ChenQi
 * @date 2023/11/20
 * @description: BusinessExceptionEnum
 */
@Getter
@ToString
public enum BusinessExceptionEnum {
    MEMBER_MOBILE_EXIST("手机号已注册"),
    MEMBER_MOBILE_NOT_EXIST("请先获取短信验证码"),
    MEMBER_MOBILE_CODE_ERROR("短信验证码错误");

    private String desc;

    BusinessExceptionEnum(String desc) {
        this.desc = desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
