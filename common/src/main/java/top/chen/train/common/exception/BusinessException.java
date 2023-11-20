package top.chen.train.common.exception;

import lombok.Getter;

/**
 * @author ChenQi
 * @date 2023/11/20
 * @description: BusinessException
 */
@Getter
public class BusinessException extends RuntimeException{
    private BusinessExceptionEnum e;

    public BusinessException(BusinessExceptionEnum e) {
        this.e = e;
    }

    public void setE(BusinessExceptionEnum e) {
        this.e = e;
    }

    /**
     * 不写入堆栈信息，提高性能
     * @return
     */
    @Override
    public Throwable fillInStackTrace() {
        return this;
    }
}
