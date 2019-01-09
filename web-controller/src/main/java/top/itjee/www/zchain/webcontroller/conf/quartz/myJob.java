package top.itjee.www.zchain.webcontroller.conf.quartz;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

import java.util.Date;

public class myJob extends QuartzJobBean {


    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
    }
}
