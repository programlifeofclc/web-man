package top.itjee.www.zchain.webcontroller.conf.activemq.listener;

import org.apache.activemq.command.ActiveMQTextMessage;
import top.itjee.www.zchain.webcontroller.conf.activemq.inter.MQMessageListener;

import javax.jms.JMSException;
import java.util.ArrayList;
import java.util.List;

public abstract class ConsumerListener {

    public List<MQMessageListener> list = new ArrayList<MQMessageListener>();

    public void notifyListener(ActiveMQTextMessage text) {
        boolean b = true;
        for (MQMessageListener listener : list) {
            try {
                b = b && listener.acceptMessage(text);
            } catch (Exception e) {
                b = false;
            }
        }
        if (b) {
            try {
                text.acknowledge();
            } catch (JMSException e) {
                e.printStackTrace();
            }
        }
    }

    public synchronized void regListener(MQMessageListener listener) {
        list.add(listener);
    }
}
