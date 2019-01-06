package top.itjee.www.zchain.webcontroller.conf;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import top.itjee.www.zchain.enums.ThreadEnum;
import top.itjee.www.zchain.service.annotation.ReadWrite;
import top.itjee.www.zchain.utils.ThreadLocalUtil;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

@Component
@Primary
public class DynamicDataSource extends AbstractDynamicDataSource {

    private Random ra = new Random();

    @Override
    public Object getTargetDataSourceKey(List<String> master, List<String> slave) throws RuntimeException {
        if (slave == null || slave.size() == 0) {
            return randomList(master);
        }
        LinkedList<ReadWrite> list = ThreadLocalUtil.get(ThreadEnum.READ_WRITE_THREAD);
        if (list != null && list.size() > 0) {
            ReadWrite rw = list.get(0);
            if (rw.value().equals(ReadWrite.option.WRITE)) {
                if(master ==null ||master.size()== 0){
                    throw new RuntimeException("主写库为空");
                }
                return randomList(master);
            } else {
                return randomList(slave);
            }
        } else {
            return randomList(master);
        }
    }

    String randomList(List<String> ms) {
        return ms.get(ra.nextInt(ms.size()));
    }


}
