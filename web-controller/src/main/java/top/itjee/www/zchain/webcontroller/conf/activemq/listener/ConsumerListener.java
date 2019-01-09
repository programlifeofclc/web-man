package top.itjee.www.zchain.webcontroller.conf.activemq.listener;

import org.apache.activemq.command.ActiveMQTextMessage;
import top.itjee.www.zchain.webcontroller.conf.activemq.inter.MQMessageHandler;

import javax.jms.JMSException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public abstract class ConsumerListener {

    public List<MQMessageHandler> list = new ArrayList<>();

    public ExecutorService pool = Executors.newFixedThreadPool(5);

    public void notifyListener(ActiveMQTextMessage text) throws Exception {
        boolean b = true;
        List<Future<Boolean>> futures = new ArrayList<>();
        for (MQMessageHandler call : list) {
            call.setMQMessage(text);
            Future<Boolean> future = pool.submit(call);
            futures.add(future);
        }
        for (Future<Boolean> future : futures) {
            try {
                b = b && future.get(3 * 60, TimeUnit.SECONDS);
            } catch (Exception e) {
                e.printStackTrace();
                b = false;
            }
        }
        if (b) {
            text.acknowledge();
        } else {
            throw new Exception("消息：" + text.getText() + "报错了");
        }
    }

    public synchronized void regListener(MQMessageHandler listener) {
        list.add(listener);
    }

}
