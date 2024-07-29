package it.prometeo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.ws.config.annotation.EnableWs;

@EnableWs
@SpringBootApplication
@EnableScheduling
public class SoapClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(SoapClientApplication.class, args);
	}

}
