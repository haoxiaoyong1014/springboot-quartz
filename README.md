### springboot-quartz

#### springboot集成quartz 任务持久化


[2019-9-1更新](#2019-9-1更新)

[2019-12-24更新](#2019-12-24更新)

* 启动项目

* 输入 http://localhost:12741/JobManager.html 会看到这个页面,在这里可以看到你的所有任务

![image](https://github.com/haoxiaoyong1014/best-pay-demo/raw/master/src/main/java/com/github/lly835/Images/q1.jpg)

* 添加任务 

![](http://cg-mall.oss-cn-shanghai.aliyuncs.com/blog/image-20201217122538397.png)

![image](http://cg-mall.oss-cn-shanghai.aliyuncs.com/blog/image-20201217122910469.png)

在添加任务的时候 `任务名称`指定类名即可，通过反射得到该类, 由于newJob和helloJob都实现了BaseJob，

所以这里不需要我们手动去判断。这里涉及到了一些java多态调用的机制

`任务名称`例如: `newJob`

这里是首字母小写;所以在前端的`任务名称`也要首字母小写;

当然持久化也是将任务添加到数据库的,sql脚本在项目的跟目录下 <a href="https://github.com/HLW-Tec/springboot-quartz/blob/master/quartz.sql">quartz</a> (官方提供的sql脚本),

在添加任务中的`表达式`你可以到 <a href="http://cron.qqe2.com/">这里自动生成</a> 

### 增加 SimpleTrigger

![image](http://cg-mall.oss-cn-shanghai.aliyuncs.com/blog/image-20201217123126052.png)

点击确定:

![image](http://cg-mall.oss-cn-shanghai.aliyuncs.com/blog/image-20201217123305348.png)

更新:

注意: 如果你当前 springboot版本是 2.1.x,就把`quartz.properties`文件中的`org.quartz.scheduler.instanceName = DefaultQuartzScheduler`注释掉;

org.quartz.scheduler.instanceName

> Can be any string, and the value has no meaning to the scheduler itself - but rather serves as a mechanism for client code to distinguish schedulers when multiple instances are used within the same program. If you are using the clustering features, you must use the same name for every instance in the cluster that is ‘logically’ the same Scheduler.

> 可以是任何字符串，并且该值对调度程序本身没有意义 - 而是作为客户端代码在同一程序中使用多个实例时区分调度程序的机制。如果使用群集功能，则必须对群集中“逻辑上”为同一个调度程序的每个实例使用相同的名称。

如果你是在集群模式下就把`SchedulerConfig`配置类中的下面这段代码注释掉:

```java
    /**
     * quartz初始化监听器
     * 这个监听器可以监听到工程的启动，在工程停止再启动时可以让已有的定时任务继续进行。
     * @return
     */
    @Bean
    public QuartzInitializerListener executorListener() {
        return new QuartzInitializerListener();
    }
```

#### 2019-9-1更新

在我博客中有人评论说使用`SimpleTrigger`类型时存库不成功,在博客中我也回复了,在这里再统一说明一下,在使用`SimpleTrigger`时,要想看到效果就要把时间设置的长一些,
因为定时任务执行完之后就会自动删除数据库中的记录;

在这里我也更正一下我的 sql 语句,之所以没有查出来`SimpleTrigger`类型的数据是因为我的 sql,表连接是用了`INNER JOIN`;在这里改为`LEFT JOIN`就可以了;这样两种类型的
都可以查询出来了;以下是我更新之后的 sql 语句;

```sql
SELECT DISTINCT
	QRTZ_JOB_DETAILS.JOB_NAME ,
	QRTZ_JOB_DETAILS.JOB_GROUP ,
	QRTZ_JOB_DETAILS.JOB_CLASS_NAME ,
	QRTZ_TRIGGERS.TRIGGER_NAME ,
	QRTZ_TRIGGERS.TRIGGER_GROUP ,
	QRTZ_CRON_TRIGGERS.CRON_EXPRESSION ,
	QRTZ_CRON_TRIGGERS.TIME_ZONE_ID
FROM
	QRTZ_JOB_DETAILS
LEFT JOIN QRTZ_TRIGGERS ON QRTZ_TRIGGERS.TRIGGER_GROUP = QRTZ_JOB_DETAILS.JOB_GROUP
LEFT JOIN QRTZ_CRON_TRIGGERS ON QRTZ_JOB_DETAILS.JOB_NAME = QRTZ_TRIGGERS.JOB_NAME
AND QRTZ_TRIGGERS.TRIGGER_NAME = QRTZ_CRON_TRIGGERS.TRIGGER_NAME
AND QRTZ_TRIGGERS.TRIGGER_GROUP = QRTZ_CRON_TRIGGERS.TRIGGER_GROUP
```

#### 2019-12-24更新

有评论说，在jobs里面@Autowired 注入service为null,今天主要解决这个问题,在JobController中弃用了反射,在tool包中加入了
SpringUtil工具类;主要目的是从Bean容器中获取指定的Bean;

**JobController**
```java
public BaseJob getClass(String classname) throws Exception {
        //Class<?> class1 = Class.forName(classname);
        //BaseJob baseJob = (BaseJob) class1.newInstance();
        BaseJob baseJob = (BaseJob) SpringUtil.getBean(classname);
        return baseJob;
    }
```
注意: 参数classnam是前端传过来的`任务名称`,注意这里的任务名称要和具体的Job类上的@Component注解中value值相同;
例如：
```java
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
```
这里是首字母小写;所以在前端的`任务名称`也要首字母小写;

在上面的HelloJob中,使用SpringUtil工具类来获取具体的Service Bean;

