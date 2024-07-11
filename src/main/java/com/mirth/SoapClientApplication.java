package com.mirth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.ws.config.annotation.EnableWs;

@EnableWs
@SpringBootApplication
public class SoapClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(SoapClientApplication.class, args);
	}

}
