package com.devx.oauth;

import feign.Logger;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableFeignClients
@EntityScan({"com.devx.commonuser.*"})
@SpringBootApplication
public class OauthApplication implements CommandLineRunner {

	private final BCryptPasswordEncoder passwordEncoder;

	public OauthApplication(
			BCryptPasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
	}

	public static void main(String[] args) {
		SpringApplication.run(OauthApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		String pass = "12345";

		for (int i=0; i<=4; i++) {
			String passBCrypt = passwordEncoder.encode(pass);
			System.out.println(passBCrypt);

		}
	}

}
