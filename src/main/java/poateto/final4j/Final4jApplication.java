package poateto.final4j;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import poateto.final4j.UseCases.Components.ApiKeySet;

@SpringBootApplication
public class Final4jApplication {

	public static void main(String[] args) {
		ApiKeySet.init();
		SpringApplication.run(Final4jApplication.class, args);
	}

}
