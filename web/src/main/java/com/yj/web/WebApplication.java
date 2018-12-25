package com.yj.web;

import com.yj.common.util.CookieUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication(
		scanBasePackages ={
				"com.yj.web",
				"com.yj.service",
				"com.yj.dal",
				"com.yj.common"
		}
)
//开启事务管理
@EnableTransactionManagement
//开启Rabbit
//@EnableRabbit
//开启定时器任务
@EnableScheduling
public class WebApplication extends SpringBootServletInitializer {
	private static final Logger logger = LoggerFactory.getLogger(WebApplication.class);

	/**
	 * 实现SpringBootServletInitializer可以让spring-boot项目在web容器中运行
	 */
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(WebApplication.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(WebApplication.class, args);
		logger.info("(∼‾▽‾)→))*‾▽‾*)o        启动成功        (∼‾▽‾)→))*‾▽‾*)o\n");
	}
}
