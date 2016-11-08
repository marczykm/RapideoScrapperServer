package pl.marczyk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class RapideoScrapperServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(RapideoScrapperServerApplication.class, args);
	}
}
