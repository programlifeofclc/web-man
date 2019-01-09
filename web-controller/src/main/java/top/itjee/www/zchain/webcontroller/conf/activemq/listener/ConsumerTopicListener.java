package top.itjee.www.zchain.webcontroller.conf.activemq.listener;

import org.apache.activemq.command.ActiveMQTextMessage;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;

@Component
public class ConsumerTopicListener extends ConsumerListener {

    @JmsListener(destination = "defaultTopic", containerFactory = "topicConnectionFactory",selector = "")
    public void receiveTopic(ActiveMQTextMessage text) throws Exception {
        notifyListener(text);
    }

}
