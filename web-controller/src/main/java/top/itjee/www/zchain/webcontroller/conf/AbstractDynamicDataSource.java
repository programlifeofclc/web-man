package top.itjee.www.zchain.webcontroller.conf;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import top.itjee.www.zchain.enums.ThreadEnum;
import top.itjee.www.zchain.utils.ThreadLocalUtil;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.util.*;


public abstract class AbstractDynamicDataSource extends AbstractRoutingDataSource {

    @Autowired
    private Environment env;

    private List<String> master = new ArrayList<>();

    private List<String> slave = new ArrayList<>();

    @Override
    protected Object determineCurrentLookupKey() {
        Object key = ThreadLocalUtil.get(ThreadEnum.DYNAMIC_DATASOURCE_KEY);
        if (key != null) {
            return key;
        }
        Object o = getTargetDataSourceKey(master, slave);
        if (o != null) {
            ThreadLocalUtil.set(ThreadEnum.DYNAMIC_DATASOURCE_KEY, o);
            return o;
        }
        return null;
    }

    public abstract Object getTargetDataSourceKey(List<String> master, List<String> slave) throws RuntimeException;

    @PostConstruct
    public void init() {

        String dynamicJdbc = env.getProperty("dynamic.jdbc");
        String dynamicDriverClassName = env.getProperty("dynamic.driverClassName");
        String dynamicMasterUrls = env.getProperty("dynamic.master.urls");
        String dynamicSlaveUrls = env.getProperty("dynamic.slave.urls");
        String dynamicUsername = env.getProperty("dynamic.username");
        String dynamicPassword = env.getProperty("dynamic.password");

        Map<Object, Object> map = new HashMap<>();
        String[] urlMasterArr = dynamicMasterUrls.split(",");
        for (int i = 0; i < urlMasterArr.length; i++) {
            try {
                String url = urlMasterArr[i];
                Properties props = new Properties();
                props.put("driverClassName", dynamicDriverClassName);
                props.put("url", dynamicJdbc.replace("$", url));
                props.put("username", dynamicUsername);
                props.put("password", dynamicPassword);
                DataSource ds = DruidDataSourceFactory.createDataSource(props);
                map.put("master_" + i, ds);
                if (i == 0) {
                    setDefaultTargetDataSource(ds);
                }
                master.add("master_" + i);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        String[] urlSlaveArr = dynamicSlaveUrls.split(",");
        for (int i = 0; i < urlSlaveArr.length; i++) {
            try {
                String url = urlSlaveArr[i];
                Properties props = new Properties();
                props.put("driverClassName", dynamicDriverClassName);
                props.put("url", dynamicJdbc.replace("$", url));
                props.put("username", dynamicUsername);
                props.put("password", dynamicPassword);
                DataSource ds = DruidDataSourceFactory.createDataSource(props);
                map.put("slave_" + i, ds);
                slave.add("slave_" + i);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        setTargetDataSources(map);
    }


    public Environment getEnv() {
        return env;
    }

    public void setEnv(Environment env) {
        this.env = env;
    }

    public List<String> getMaster() {
        return master;
    }

    public void setMaster(List<String> master) {
        this.master = master;
    }

    public List<String> getSlave() {
        return slave;
    }

    public void setSlave(List<String> slave) {
        this.slave = slave;
    }
}
