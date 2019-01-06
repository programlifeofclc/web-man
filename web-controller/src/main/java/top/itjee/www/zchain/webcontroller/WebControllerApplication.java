package top.itjee.www.zchain.webcontroller;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableJms
@EnableSwagger2
@ServletComponentScan("top.itjee.www")//servlet filter 扫描
@EnableAspectJAutoProxy(exposeProxy = true, proxyTargetClass = true)
@SpringBootApplication(scanBasePackages = {"top.itjee.www.zchain"}) //springboot基础包扫描
@EnableRedisHttpSession(maxInactiveIntervalInSeconds = 1800)
public class WebControllerApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebControllerApplication.class, args);
    }

}




