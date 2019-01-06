package top.itjee.www.zchain.webcontroller.conf.activemq.impl;

import org.apache.activemq.command.ActiveMQMessage;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import top.itjee.www.zchain.webcontroller.conf.activemq.inter.MQMessageHandler;
import top.itjee.www.zchain.webcontroller.conf.activemq.inter.QueueMQMessageHandler;
import top.itjee.www.zchain.webcontroller.conf.activemq.inter.TopicMQMessageHandler;
import top.itjee.www.zchain.webcontroller.conf.activemq.listener.ConsumerQueueListener;
import top.itjee.www.zchain.webcontroller.conf.activemq.listener.ConsumerTopicListener;

public abstract class AbstractMQMessageHandler implements MQMessageHandler, InitializingBean {

    @Autowired
    private ConsumerTopicListener consumerTopicListener;

    @Autowired
    private ConsumerQueueListener consumerQueueListener;

    @Override
    public abstract void setMQMessage(ActiveMQMessage activeMQMessage);

    @Override
    public abstract boolean handlerMessage();

    @Override
    public final Boolean call() throws Exception {
        return this.handlerMessage();
    }

    public void afterPropertiesSet() throws Exception {
        if (this instanceof QueueMQMessageHandler) {
            consumerQueueListener.regListener(this);
        } else if (this instanceof TopicMQMessageHandler) {
            consumerTopicListener.regListener(this);
        }
    }
}
