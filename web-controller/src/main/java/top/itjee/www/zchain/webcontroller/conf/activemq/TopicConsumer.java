package top.itjee.www.zchain.webcontroller.conf.activemq;

import org.apache.activemq.command.ActiveMQMessage;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import top.itjee.www.zchain.webcontroller.conf.activemq.impl.TopicMQMessageListenerImpl;

@Component
public class TopicConsumer extends TopicMQMessageListenerImpl {

    public boolean acceptMessage(ActiveMQMessage message) {
        System.out.println("VVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVV");
        System.out.println(message.getMessage());
        return true;
    }
}
