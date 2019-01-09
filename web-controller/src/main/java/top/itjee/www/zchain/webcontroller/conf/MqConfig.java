package top.itjee.www.zchain.webcontroller.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.scheduling.annotation.EnableScheduling;

import javax.jms.ConnectionFactory;

@Configuration
@EnableScheduling
@EnableJms
public class MqConfig {

    @Bean("topicConnectionFactory")
    public JmsListenerContainerFactory<?> topicListenerFactory(ConnectionFactory connectionFactory) {
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        factory.setPubSubDomain(true);
        factory.setSessionAcknowledgeMode(2);//设置为2 否则消息失败不会重发
        factory.setConnectionFactory(connectionFactory);
        return factory;
    }

    @Bean("queueConnectionFactory")
    public JmsListenerContainerFactory<?> queueListenerFactory(ConnectionFactory connectionFactory) {
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        factory.setPubSubDomain(false);
        factory.setSessionAcknowledgeMode(2);//设置为2 否则消息失败不会重发
        factory.setConnectionFactory(connectionFactory);
        return factory;
    }

}