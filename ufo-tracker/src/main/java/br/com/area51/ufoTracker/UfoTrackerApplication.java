package br.com.area51.ufoTracker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class UfoTrackerApplication {

	public static void main(String[] args) {
		SpringApplication.run(UfoTrackerApplication.class, args);
	}

}
