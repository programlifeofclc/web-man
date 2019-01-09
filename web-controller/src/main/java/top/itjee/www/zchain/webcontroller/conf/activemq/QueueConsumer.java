package top.itjee.www.zchain.webcontroller.conf.activemq;

import org.apache.activemq.command.ActiveMQMessage;
import org.apache.activemq.command.ActiveMQTextMessage;
import org.springframework.stereotype.Component;
import top.itjee.www.zchain.webcontroller.conf.activemq.impl.AbstractQueueMQMessageHandler;

import javax.jms.JMSException;


@Component
public class QueueConsumer extends AbstractQueueMQMessageHandler {

    ActiveMQMessage activeMQMessage;

    @Override
    public void setMQMessage(ActiveMQMessage activeMQMessage) {
        this.activeMQMessage = activeMQMessage;
    }

    @Override
    public boolean handlerMessage() {
        try {
            System.out.println("主机queue获得消息：" + ((ActiveMQTextMessage)activeMQMessage).getText());
        } catch (JMSException e) {
            e.printStackTrace();
        }
        return false;
    }
}
