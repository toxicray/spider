package com.ray.spiderprice.rabbitmq;

import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Package:com.ray.spiderprice.rabbitmq
 * *Author:ray
 * *version:...
 * *Created in 2020/4/22  16:17
 **/
@SpringBootTest
public class MqTest {


	@Autowired
	private RabbitTemplate rabbitTemplate;
	@Test
	public void testSend(){
		rabbitTemplate.convertAndSend("ray1","我就看看");
	}


}
