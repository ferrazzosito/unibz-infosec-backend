package it.unibz.infosec.examproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
public class ExamProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExamProjectApplication.class, args);
	}

}
