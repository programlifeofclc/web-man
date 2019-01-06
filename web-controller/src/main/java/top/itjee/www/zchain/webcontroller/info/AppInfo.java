package top.itjee.www.zchain.webcontroller.info;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppInfo {

    @Value("${app.info.name}")
    public String appName;

    @Value("${app.info.version}")
    public String version;

}
