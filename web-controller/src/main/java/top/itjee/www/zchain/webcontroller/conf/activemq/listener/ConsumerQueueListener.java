package top.itjee.www.zchain.webcontroller.conf.activemq.listener;

import org.apache.activemq.command.ActiveMQTextMessage;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;

@Component
public class ConsumerQueueListener extends ConsumerListener {

    @JmsListener(destination = "defaultQueue", containerFactory = "queueConnectionFactory")
    public void receiveQueue(ActiveMQTextMessage text) {
        notifyListener(text);
    }

}
