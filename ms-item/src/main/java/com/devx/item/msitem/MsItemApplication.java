package com.devx.item.msitem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.openfeign.EnableFeignClients;

//@RibbonClient(name = "ms-product")
@EnableCircuitBreaker
@EnableFeignClients
@SpringBootApplication
public class MsItemApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsItemApplication.class, args);
	}

}
