package com.example.check.async.rest.config;

import java.util.concurrent.Executor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.client.RestTemplate;

@Configuration
@EnableAsync(proxyTargetClass=true)
public class ServiceConfiguration {
	
	@Bean(name = "asyncExecutor")
	public Executor asyncExecutor() {
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(3);
		// new threads will be created for new tasks upto first 3 tasks. and
		// other tasks will be added to the queue until queue is getting full
		// (100 tasks).
		executor.setMaxPoolSize(5);
		// JVM can create max 5 threads. Means if there are already 3
		// task/thread is running and queue is full with 100 pending tasks and
		// if one more new request/task is arriving in queue then JVM will create
		// new thread up to 5 (total threads=previous 3 + new 2);
		executor.setQueueCapacity(100);
		// is a total queue size - it can queue 100 tasks in it.
		executor.setThreadNamePrefix("AsyncThread-");
		executor.initialize();
		return executor;
	}
	
	@Bean(name = "simpleAsyncExecutor")
	public Executor simpleAsyncExecutor() {
		return new SimpleAsyncTaskExecutor("AsyncThread-");
	}
	
	@Bean
	public RestTemplate restTemplate(){
		return new RestTemplate();
	}
}