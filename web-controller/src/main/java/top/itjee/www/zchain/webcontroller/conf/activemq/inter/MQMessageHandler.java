package top.itjee.www.zchain.webcontroller.conf.activemq.inter;

import org.apache.activemq.command.ActiveMQMessage;

import java.util.concurrent.Callable;

public interface MQMessageHandler<T> extends Callable<T> {

    void setMQMessage(ActiveMQMessage activeMQMessage);

    boolean handlerMessage();

}
