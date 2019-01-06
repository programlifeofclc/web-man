package top.itjee.www.zchain.webcontroller.conf.activemq;

import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import javax.jms.Destination;

@Component
public class Producer {

    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;

    public void sendQueueMessage(Destination destination, final String message) {
        System.out.println("Queue名:"+ destination +"|消息:" + message);
        jmsMessagingTemplate.convertAndSend(destination, message);
    }

    public void sendQueueMessage(final String message) {
        ActiveMQQueue activeMQQueue = new ActiveMQQueue("defaultQueue");
        sendQueueMessage(activeMQQueue, message);
    }

    public void sendTopicMessage(Destination destination, final String message) {
        System.out.println("Topic名:"+ destination +"|消息:" + message);
        jmsMessagingTemplate.convertAndSend(destination, message);
    }

    public void sendTopicMessage(final String message) {
        ActiveMQTopic activeMQTopic = new ActiveMQTopic("defaultTopic");
        sendTopicMessage(activeMQTopic, message);
    }

    @Autowired
    Producer producer;

    @Scheduled(fixedDelay = 5000) // 5s执行一次   只有无参的方法才能用该注解
    public void sendMessage() {
        System.out.println("消息发送一次");
        producer.sendTopicMessage("baaaaaaaaa");
    }

    @Scheduled(fixedDelay = 4000) // 5s执行一次   只有无参的方法才能用该注解
    public void sendMessagse() {
        System.out.println("消息发送一次");
        producer.sendQueueMessage("bbbbbbbbbbbbbbbbbbbb");
    }
}
