package com.mastering.spring.taskscheduling;

import java.util.concurrent.Future;
import java.util.stream.IntStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;

@Component
public class AsyncTask {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Async
	void doThisAsynchronously() {
		IntStream.range(1, 100).forEach(x -> logger.info("AsyncTask {}",x));
	}
	
	@Async
	Future<Long> doThisAsynchronouslyAndReturnAValue() {
		IntStream.range(1, 100).forEach(x -> logger.info("AsyncTask With Return Value {}",x));		
		
		long sum = IntStream.range(1, 100).sum();
		
		return new AsyncResult<>(sum);
	}
}
