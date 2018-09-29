package com.example.quartz.service;


import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * Created by haoxy on 2018/9/28.
 * E-mail:hxyHelloWorld@163.com
 * github:https://github.com/haoxiaoyong1014
 */
public interface BaseJob extends Job {

    public void execute(JobExecutionContext context) throws JobExecutionException;
}
