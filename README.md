### springboot-quartz

#### springboot集成quartz 任务持久化


* 启动项目

* 输入 http://localhost:12741/JobManager.html 会看到这个页面,在这里可以看到你的所有任务

![image](https://github.com/haoxiaoyong1014/best-pay-demo/raw/master/src/main/java/com/github/lly835/Images/q1.jpg)

* 添加任务 

![image](https://github.com/haoxiaoyong1014/best-pay-demo/raw/master/src/main/java/com/github/lly835/Images/q3.jpg)

在添加任务的时候 `任务名称`指定类名即可，通过反射得到该类, 由于NewJob和HelloJob都实现了BaseJob，

所以这里不需要我们手动去判断。这里涉及到了一些java多态调用的机制

`任务名称`例如: `com.example.quartz.jobs.HelloJob`

当然持久化也是将任务添加到数据库的,sql脚本在项目的跟目录下 <a href="https://github.com/HLW-Tec/springboot-quartz/blob/master/quartz.sql">quartz</a> (官方提供的sql脚本),

在添加任务中的`表达式`你可以到 <a href="http://cron.qqe2.com/">这里自动生成</a> 

### 增加 SimpleTrigger

![image](https://github.com/haoxiaoyong1014/best-pay-demo/raw/master/src/main/java/com/github/lly835/Images/q4.jpg)

点击确定:

![image](https://github.com/haoxiaoyong1014/best-pay-demo/raw/master/src/main/java/com/github/lly835/Images/q5.jpg)




