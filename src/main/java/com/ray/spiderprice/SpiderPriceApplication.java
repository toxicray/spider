package com.ray.spiderprice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@EnableScheduling
@MapperScan(basePackages = "com.ray.spiderprice.mapper")
public class SpiderPriceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpiderPriceApplication.class, args);
	}

}
