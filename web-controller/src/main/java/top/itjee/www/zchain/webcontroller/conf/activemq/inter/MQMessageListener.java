package top.itjee.www.zchain.webcontroller.conf.activemq.inter;

import org.apache.activemq.command.ActiveMQMessage;

public interface MQMessageListener {

    boolean acceptMessage(ActiveMQMessage message);

}
