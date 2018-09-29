package com.example.quartz;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@MapperScan("com.example.quartz.dao")
@ComponentScan("com.example.quartz")
public class SpringbootQuartzApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootQuartzApplication.class, args);
	}
}
