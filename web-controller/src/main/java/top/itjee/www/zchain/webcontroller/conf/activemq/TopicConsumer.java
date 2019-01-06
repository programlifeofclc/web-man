package top.itjee.www.zchain.webcontroller.conf.activemq;

import org.apache.activemq.command.ActiveMQMessage;
import org.springframework.stereotype.Component;
import top.itjee.www.zchain.webcontroller.conf.activemq.impl.AbstractTopicMQMessageHandler;

@Component
public class TopicConsumer extends AbstractTopicMQMessageHandler {

    ActiveMQMessage activeMQMessage;

    @Override
    public void setMQMessage(ActiveMQMessage activeMQMessage) {
        this.activeMQMessage = activeMQMessage;
    }

    @Override
    public boolean handlerMessage() {
        System.out.println("topic获得消息：" + activeMQMessage.getMessage());
        return true;
    }
}
