package com.marshall.sky.comment;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@MapperScan("com.marshall.sky.comment.mapper")
@EnableEurekaClient
@EnableFeignClients
public class SkyCommentApplication {

	public static void main(String[] args) {
		SpringApplication.run(SkyCommentApplication.class, args);
	}

}
