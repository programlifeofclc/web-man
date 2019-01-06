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
        factory.setSessionAcknowledgeMode(4);
        factory.setConnectionFactory(connectionFactory);
        return factory;
    }

    @Bean("queueConnectionFactory")
    public JmsListenerContainerFactory<?> queueListenerFactory(ConnectionFactory connectionFactory) {
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        factory.setPubSubDomain(false);
        factory.setSessionAcknowledgeMode(4);//设置为2 会出现自动确认 所以设置为4
        factory.setConnectionFactory(connectionFactory);
        return factory;
    }

}