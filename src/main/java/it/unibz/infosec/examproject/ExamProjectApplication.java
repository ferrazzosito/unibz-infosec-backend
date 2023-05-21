package it.unibz.infosec.examproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
@EntityScan("it.unibz.infosec.examproject")
public class ExamProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExamProjectApplication.class, args);
	}

}
