package com.yj.web;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WebApplicationTests {

	@Autowired
	private RabbitTemplate rabbitTemplate;

	@Autowired
	private AmqpAdmin amqpAdmin;

	/**
	 * 测试AmqpAdmin管理功能组件
	 * 功能：创建/删除 交换机、队列、绑定
	 */
	@Test
	public void amqpAdminTest() {
		String path="http://127.0.0.1:8084//files/avatar/1fd84ae2-3876-47c0-bfa9-61e2d9a49b04.jpg";
		path="/"+path.substring(path.indexOf('a'),path.length());
		System.out.println(path);
		//创建交换机
		//amqpAdmin.declareExchange(new DirectExchange("amqpAdmin.exchange"));

		//创建队列
		//amqpAdmin.declareQueue(new Queue("amqpAdmin.queue",true));

		//创建绑定
		//amqpAdmin.declareBinding(new Binding("amqpAdmin.queue",Binding.DestinationType.QUEUE,"amqpAdmin.exchange","amqpAdmin.queue",null));
	}

	/**
	 * 发送数据
	 */
	@Test
	public void contextLoads() {
		Map<String , Object> map = new HashMap<>();
        map.put("code",200);
        map.put("data",12346767);
        rabbitTemplate.convertAndSend("exchange.direct","ou",map);
	}

	/**
	 * 接收数据
	 */
	@Test
	public void receive() {
		Object object = rabbitTemplate.receiveAndConvert("ou");
		//System.out.println(object.getClass());
		System.out.println("接收的数据 = "+object);
	}
}
