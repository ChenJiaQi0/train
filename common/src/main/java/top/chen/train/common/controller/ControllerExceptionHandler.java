package top.chen.train.common.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import top.chen.train.common.exception.BusinessException;
import top.chen.train.common.resp.CommonResp;

/**
 * @author ChenQi
 * @date 2023/11/20
 * @description: ControllerExceptionHandler
 */
@ControllerAdvice
public class ControllerExceptionHandler {
    private static final Logger LOG = LoggerFactory.getLogger(ControllerExceptionHandler.class);

    /**
     * 业务异常统一处理
     * @param e 异常
     * @return CommonResp
     */
    @ExceptionHandler(value = BusinessException.class)
    @ResponseBody
    public CommonResp<?> exceptionHandler(BusinessException e) {
        CommonResp<?> commonResp = new CommonResp<>();
        LOG.error("业务异常：{}" + e.getE().getDesc());
        commonResp.setSuccess(false);
        commonResp.setMessage(e.getE().getDesc());
        return commonResp;
    }

    /**
     * 所有异常统一处理
     * @param e
     * @return
     * @throws Exception
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public CommonResp<?> exceptionHandler(Exception e) throws Exception {
        CommonResp<?> commonResp = new CommonResp<>();
        LOG.error("系统异常：" + e);
        commonResp.setSuccess(false);
        commonResp.setMessage(e.getMessage());
        return commonResp;
    }
}
