package top.itjee.www.zchain.webcontroller.conf.activemq.impl;

import org.apache.activemq.command.ActiveMQMessage;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import top.itjee.www.zchain.webcontroller.conf.activemq.inter.MQMessageListener;
import top.itjee.www.zchain.webcontroller.conf.activemq.inter.QueueMQMessageListener;
import top.itjee.www.zchain.webcontroller.conf.activemq.inter.TopicMQMessageListener;
import top.itjee.www.zchain.webcontroller.conf.activemq.listener.ConsumerQueueListener;
import top.itjee.www.zchain.webcontroller.conf.activemq.listener.ConsumerTopicListener;

public abstract class AbstractMQMessageListener implements MQMessageListener, InitializingBean, BeanPostProcessor {

    @Autowired
    private ConsumerTopicListener consumerTopicListener;

    @Autowired
    private ConsumerQueueListener consumerQueueListener;

    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        //每个bean都会执行
        return bean;
    }

    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        //每个bean都会执行
        return bean;
    }

    public void afterPropertiesSet() throws Exception {
        if (this instanceof QueueMQMessageListener) {
            consumerQueueListener.regListener(this);
        } else if (this instanceof TopicMQMessageListener) {
            consumerTopicListener.regListener(this);
        }
    }
}
