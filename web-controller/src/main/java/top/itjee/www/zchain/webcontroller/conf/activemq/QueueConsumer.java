package top.itjee.www.zchain.webcontroller.conf.activemq;

import org.apache.activemq.command.ActiveMQMessage;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import top.itjee.www.zchain.webcontroller.conf.activemq.impl.QueueMQMessageListenerImpl;


@Component
public class QueueConsumer extends QueueMQMessageListenerImpl {

    public boolean acceptMessage(ActiveMQMessage message) {
        System.out.println("sfasfdasdf");
        System.out.println(message.getMessage());

        return true;
    }
}
