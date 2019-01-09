package top.itjee.www.zchain.webcontroller.conf.activemq.listener;

import org.apache.activemq.command.ActiveMQTextMessage;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class DLQQueueListener extends ConsumerListener {

    //死信队列默认名字ActiveMQ.DLQ 可通过配置修改
    @JmsListener(destination = "ActiveMQ.DLQ", containerFactory = "queueConnectionFactory", selector = "")
    public void receiveQueue(ActiveMQTextMessage text) throws Exception {
        System.out.println("死信息：" + text.getText());
    }

}
