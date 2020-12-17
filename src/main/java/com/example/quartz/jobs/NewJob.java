package com.example.quartz.jobs;

import com.example.quartz.service.BaseJob;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Created by haoxy on 2018/9/28.
 * E-mail:hxyHelloWorld@163.com
 * github:https://github.com/haoxiaoyong1014
 */
@Component("newJob")
public class NewJob implements BaseJob{

    private static Logger log = LoggerFactory.getLogger(NewJob.class);

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        log.info("New Job执行时间: " + DateFormatUtils.format(new Date(),"yyyy-MM-dd HH:mm:ss"));
    }

    public NewJob() {}
}
