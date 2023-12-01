package top.chen.train.business.mq;

import com.alibaba.fastjson.JSON;
import jakarta.annotation.Resource;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import top.chen.train.business.req.ConfirmOrderDoReq;
import top.chen.train.business.service.ConfirmOrderService;

/**
 * @author ChenQi
 * @date 2023/12/1
 * @description: ConfirmOrderConsumer
 */
@Service
@RocketMQMessageListener(consumerGroup = "default", topic = "CONFIRM_ORDER_JQ")
public class ConfirmOrderConsumer implements RocketMQListener<MessageExt> {
    private static final Logger LOG = LoggerFactory.getLogger(ConfirmOrderConsumer.class);

    @Resource
    private ConfirmOrderService confirmOrderService;

    @Override
    public void onMessage(MessageExt messageExt) {
        byte[] body = messageExt.getBody();
        LOG.info("ROCKETMQ 收到消息：{}", new String(body));
        ConfirmOrderDoReq req = JSON.parseObject(new String(body), ConfirmOrderDoReq.class);
        confirmOrderService.doConfirm(req);
    }
}
