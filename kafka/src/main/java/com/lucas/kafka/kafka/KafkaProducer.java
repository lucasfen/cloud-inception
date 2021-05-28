package com.lucas.kafka.kafka;


import java.util.concurrent.TimeUnit;
import javax.annotation.Resource;

import com.etekcity.cloud.ErrorCode;
import com.etekcity.cloud.monitor.MetricManager;
import com.etekcity.cloud.monitor.RequestMetric;
import com.etekcity.cloud.msg.Message;
import com.etekcity.cloud.msg.MsgContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.util.concurrent.ListenableFutureCallback;

/**
 * @Author: Jimmy
 * @Date: 2019-12-27 15:51
 */
@Component
@Slf4j
public class KafkaProducer {

    @Resource
    private KafkaTemplate<String, String> kafkaTemplate;

    /**
     * 异步发送消息
     *
     * @param topic 不可以为null
     * @param key 不可以为null, 设备消息用cid，用户消息用accountId
     */
    public void sendAsync(String topic, String key, Message<? extends MsgContext> message) {
        sendRawMsgAsync(topic, key, message.toString(), message.getContext().getSendTime());
    }

    /**
     * 异步发送消息
     *
     * @param key 不可以为null
     */
    public void sendRawMsgAsync(String topic, String key, String message) {
        sendRawMsgAsync(topic, key, message, null);
    }

    /**
     * 异步发送消息
     *
     * @param key 不可以为null, 设备消息用cid，用户消息用accountId
     * @param
     */
    private void sendRawMsgAsync(String topic, String key, String message, Long timestamp) {
        if (!StringUtils.hasText(key)) {
            throw new IllegalArgumentException("kakfa partition key should not be null");
        }
        RequestMetric.Context context = MetricManager.getOrRegisterOutReq("kafka-" + topic).time();
        kafkaTemplate.send(topic, null, timestamp, key, message)
                .addCallback(new ListenableFutureCallback<SendResult<String, String>>() {
                    @Override
                    public void onFailure(Throwable ex) {
                        context.stop(ErrorCode.INTERNAL_MQ_KAFKA_PRODUCE_ERROR);
                        log.error("kafka send topic [{}]  message [{}] error", topic, message, ex);
                    }

                    @Override
                    public void onSuccess(SendResult<String, String> result) {
                        context.stop(ErrorCode.SUCCESS);
                        log.debug("kafka topic [{}]  message [{}]  send, result{}", topic,
                                message, result.getProducerRecord());
                    }
                });
    }

    /**
     * 同步发送消息
     *
     * @param key 不可以为null, 设备消息用cid，用户消息用accountId
     */

    public SendResult<String, String> sendSync(String topic, String key, Message<? extends MsgContext> message) {
        RequestMetric.Context context = MetricManager.getOrRegisterOutReq("kafka-" + topic).time();
        SendResult<String, String> sendResult = null;
        ErrorCode errorCode = ErrorCode.SUCCESS;
        try {
            sendResult = kafkaTemplate.send(topic, null, message.getContext().getSendTime(), key, message.toString())
                    .get();
        } catch (Exception e) {
            errorCode = ErrorCode.INTERNAL_MQ_KAFKA_PRODUCE_ERROR;
        } finally {
            context.stop(errorCode);
        }
        return sendResult;
    }

    /**
     * 同步带等待时间发送消息
     *
     * @param key 不可以为null, 设备消息用cid，用户消息用accountId
     * @param timeOutSec 单位秒
     */
    public SendResult<String, String> sendWithTimeOut(String topic, String key, Message<? extends MsgContext> message, Long timeOutSec) {
        RequestMetric.Context context = MetricManager.getOrRegisterOutReq("kafka-" + topic).time();
        SendResult<String, String> sendResult = null;
        ErrorCode errorCode = ErrorCode.SUCCESS;
        try {
            sendResult = kafkaTemplate.send(topic, null, message.getContext().getSendTime(), key, message.toString())
                    .get(timeOutSec, TimeUnit.SECONDS);
        } catch (Exception e) {
            errorCode = ErrorCode.INTERNAL_MQ_KAFKA_PRODUCE_ERROR;
        } finally {
            context.stop(errorCode);
        }
        return sendResult;
    }

}
