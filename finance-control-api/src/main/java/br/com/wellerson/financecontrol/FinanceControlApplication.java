package br.com.wellerson.financecontrol;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@EnableConfigurationProperties(OpenAiProperties.class);
@SpringBootApplication
public class FinanceControlApplication {

	public static void main(String[] args) {
		SpringApplication.run(FinanceControlApplication.class, args);
	}

}
