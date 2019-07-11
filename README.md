### springboot-quartz

#### springboot集成quartz 任务持久化


* 启动项目

* 输入 http://localhost:12741/JobManager.html 会看到这个页面,在这里可以看到你的所有任务

![image](https://github.com/haoxiaoyong1014/best-pay-demo/raw/master/src/main/java/com/github/lly835/Images/q1.jpg)

* 添加任务 

![image](https://github.com/haoxiaoyong1014/best-pay-demo/raw/master/src/main/java/com/github/lly835/Images/q3.jpg)

在添加任务的时候 `任务名称`指定类名即可，通过反射得到该类, 由于NewJob和HelloJob都实现了BaseJob，

所以这里不需要我们手动去判断。这里涉及到了一些java多态调用的机制

`任务名称`例如: `HelloJob`

当然持久化也是将任务添加到数据库的,sql脚本在项目的跟目录下 <a href="https://github.com/HLW-Tec/springboot-quartz/blob/master/quartz.sql">quartz</a> (官方提供的sql脚本),

在添加任务中的`表达式`你可以到 <a href="http://cron.qqe2.com/">这里自动生成</a> 

### 增加 SimpleTrigger

![image](https://github.com/haoxiaoyong1014/best-pay-demo/raw/master/src/main/java/com/github/lly835/Images/q4.jpg)

点击确定:

![image](https://github.com/haoxiaoyong1014/best-pay-demo/raw/master/src/main/java/com/github/lly835/Images/q5.jpg)

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

