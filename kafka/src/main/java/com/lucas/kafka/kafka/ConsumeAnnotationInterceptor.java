package com.lucas.kafka.kafka;

import java.util.concurrent.Executor;

import com.lucas.kafka.annotation.KafkaPool;
import lombok.extern.slf4j.Slf4j;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.core.Ordered;
import org.springframework.core.env.Environment;
import org.springframework.kafka.annotation.KafkaListener;

/**
 * @Author: Jimmy
 * @Date: 2019-08-27 15:52
 */
@Slf4j
public class ConsumeAnnotationInterceptor implements MethodInterceptor, Ordered {

    private Executor defaultExecutor;

    private Environment environment;

    private BeanFactory beanFactory;

    public ConsumeAnnotationInterceptor(Executor executor, Environment environment, BeanFactory beanFactory) {
        this.defaultExecutor = executor;
        this.environment = environment;
        this.beanFactory = beanFactory;
    }

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        KafkaListener kafkaListener = invocation.getMethod().getAnnotation(KafkaListener.class);
        if (kafkaListener == null) {
            //不是被kafkaListener标示的方法不放进线程池
            return invocation.proceed();
        }
        KafkaPool kafkaPool = invocation.getMethod().getAnnotation(KafkaPool.class);
        Executor executor = defaultExecutor;

        boolean poolEnable = kafkaPool == null || kafkaPool.poolEnable();

        if (kafkaPool != null && poolEnable) {
            try {
                executor = (Executor) beanFactory.getBean(kafkaPool.value());
            } catch (Exception e) {
                log.warn("自定义kafka线程池配置错误，请检查，该topic消费改为使用默认线程池", e);
            }
        }
        Object[] content = invocation.getArguments();
        String[] topics = kafkaListener.topics();

        String message = content[0] instanceof String ? (String) content[0] : null;
        handleMsg(message,
                getMetricName(topics[0]),
                invocation::proceed, executor, poolEnable);
        return null;
    }


    @Override
    public int getOrder() {
        return LOWEST_PRECEDENCE;
    }


    private void handleMsg(String content, String topic, ExceptionalFunction function, Executor executor, boolean poolEnable) {
        Runnable runnable = () -> {
            // add monitor
            //RequestMetric.Context context = MetricManager.getOrRegisterInReq("kafka-" + topic).time();
            //RequestMetric.Context kafkaMetricContext = MetricManager.getOrRegisterInReq("kafka.msg").time();
            //ErrorCode errorCode = ErrorCode.SUCCESS;
//            try {
//                function.apply();
//            } catch (IotBizException e) {
//                log.warn("kafka处理消息失败，topic:{}, message:{}", topic, content, e);
//                errorCode = e.getErrorCode();
//            } catch (IotNonBizException e) {
//                log.error("kafka处理消息失败，topic:{},message:{}", topic, content, e);
//                errorCode = e.getErrorCode();
//            } catch (Throwable e) {
//                log.error("kafka处理消息失败，topic:{},message:{}", topic, content, e);
//                errorCode = ErrorCode.INTERNAL_ERROR;
//            } finally {
//                //context.stop(errorCode);
//                //kafkaMetricContext.stop(errorCode);
//            }
            try {
                function.apply();
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }
        };
        if (poolEnable) {
            executor.execute(runnable);
        } else {
            runnable.run();
        }

    }

    private String getMetricName(String topic) {
        return environment.getProperty(topic.substring("${".length(), topic.length() - "}".length()));
    }

    public interface ExceptionalFunction {

        void apply() throws Throwable;
    }
}
