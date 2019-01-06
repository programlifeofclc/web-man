package top.itjee.www.zchain.webcontroller.conf;

import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.boot.autoconfigure.ConfigurationCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import javax.sql.DataSource;

@Configuration
@MapperScan("top.itjee.www.zchain.dao")//mybatis mapper扫描
public class MybatisConfig implements ConfigurationCustomizer {

    @Override
    public void customize(org.apache.ibatis.session.Configuration configuration) {

    }

}
