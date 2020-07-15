package com.ray.spiderprice.mqconsumer;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * Package:com.ray.spiderprice.mqconsumer
 * *Author:ray
 * *version:...
 * *Created in 2020/4/22  16:21
 **/
@Component
//@RabbitListener(queues = "ray1")
public class Customer1 {

	@RabbitHandler
	public void consumer(String msg){
		System.out.println(msg);
		System.out.println("消费消息成功");
	}
}
