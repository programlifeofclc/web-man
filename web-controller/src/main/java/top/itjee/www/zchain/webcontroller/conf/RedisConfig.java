package top.itjee.www.zchain.webcontroller.conf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.core.env.Environment;
import org.springframework.data.redis.connection.RedisSentinelConfiguration;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisSentinelPool;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.Set;

@Configuration
public class RedisConfig {

    @Autowired
    private Environment env;

    @Autowired
    JedisPoolConfig jedisPoolConfig;

    @Autowired
    JedisSentinelPool jedisSentinelPool;

    @Bean
    @ConfigurationProperties("redis")
    public JedisPoolConfig getJedisPoolConfig() {
        return new JedisPoolConfig();
    }

    @Bean
    public JedisSentinelPool getCuratorFramework() {
        String name = env.getProperty("redis.sentinel.master");
        String nodes = env.getProperty("redis.sentinel.nodes");
        String password = env.getProperty("redis.sentinel.password");
        Set<String> set = new HashSet<>();
        String[] nodesArr = nodes.split(",");
        for (String s : nodesArr) {
            set.add(s);
        }
        JedisSentinelPool jedisSentinelPool = new JedisSentinelPool(name, set, jedisPoolConfig, password);
        return jedisSentinelPool;
    }

}