package schema.example.schemarelation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class SchemarelationApplication {

	public static void main(String[] args) {
		SpringApplication.run(SchemarelationApplication.class, args);
	}

}
