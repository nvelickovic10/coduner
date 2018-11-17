package com.nvelickovic10.coduner;

import java.util.concurrent.Executors;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.concurrent.ConcurrentTaskExecutor;

@SpringBootApplication
public class CodunerApplication {

	@Bean(name = "ConcurrentTaskExecutor")
	public TaskExecutor taskExecutor() {
		return new ConcurrentTaskExecutor(Executors.newFixedThreadPool(1));
	}

	public static void main(String[] args) {
		SpringApplication.run(CodunerApplication.class, args);
	}
}
