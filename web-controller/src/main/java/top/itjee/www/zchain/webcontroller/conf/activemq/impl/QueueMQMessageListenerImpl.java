package top.itjee.www.zchain.webcontroller.conf.activemq.impl;

import org.apache.activemq.command.ActiveMQMessage;
import org.springframework.stereotype.Component;
import top.itjee.www.zchain.webcontroller.conf.activemq.inter.QueueMQMessageListener;

public abstract class QueueMQMessageListenerImpl extends AbstractMQMessageListener implements QueueMQMessageListener {

}
