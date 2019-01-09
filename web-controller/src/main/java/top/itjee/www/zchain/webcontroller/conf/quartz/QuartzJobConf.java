package top.itjee.www.zchain.webcontroller.conf.quartz;

import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QuartzJobConf {

    /**
     * springboot2 不用再实现SchedulerFactoryBean , QuartzAutoConfiguration 已经实现
     */

    @Autowired
    JobDetail jobDetail;
    /**
     * 创建CronTrigger
     * @return
     */
    @Bean
    public CronTrigger getCronTrigger() {
        CronTrigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("myTrigger", "clcGroup")
                .withSchedule(CronScheduleBuilder.cronSchedule("*/1 * * * * ?"))
                .forJob(jobDetail.getKey())
                .build();
        return trigger;
    }

    /**
     * 创建JobDetail
     * @return
     */
    @Bean
    public JobDetail getJobDetail() {
        return JobBuilder.newJob(myJob.class)
                .withIdentity("myJob", "clcGroup")
                .storeDurably(true)
                .build();
    }

}
