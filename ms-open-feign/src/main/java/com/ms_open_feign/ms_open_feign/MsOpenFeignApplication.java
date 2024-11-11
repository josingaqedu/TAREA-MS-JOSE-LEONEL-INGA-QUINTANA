package com.ms_open_feign.ms_open_feign;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class MsOpenFeignApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsOpenFeignApplication.class, args);
	}

}
