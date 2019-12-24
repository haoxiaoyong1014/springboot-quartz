package com.example.quartz.jobs;

import com.example.quartz.model.JobAndTrigger;
import com.example.quartz.service.BaseJob;
import com.example.quartz.service.IJobAndTriggerService;
import com.example.quartz.tool.SpringUtil;
import com.github.pagehelper.PageInfo;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Date;

/**
 * Created by haoxy on 2018/9/28.
 * E-mail:hxyHelloWorld@163.com
 * github:https://github.com/haoxiaoyong1014
 */
@Component("helloJob")
public class HelloJob implements BaseJob {

    private static Logger log = LoggerFactory.getLogger(HelloJob.class);

    public HelloJob() {

    }

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        IJobAndTriggerService iJobAndTriggerService = (IJobAndTriggerService) SpringUtil.getBean("IJobAndTriggerServiceImpl");
        PageInfo<JobAndTrigger> jobAndTriggerDetails = iJobAndTriggerService.getJobAndTriggerDetails(1, 10);
        System.out.println(jobAndTriggerDetails.getTotal());
        log.info("Hello Job执行时间: " + new Date());
    }
}

