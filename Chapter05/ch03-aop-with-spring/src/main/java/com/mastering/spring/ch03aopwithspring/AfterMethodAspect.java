package com.mastering.spring.ch03aopwithspring;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

//AOP
//Configuration
@Aspect
@Configuration
public class AfterMethodAspect {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@After("execution(* com.mastering.spring.ch03aopwithspring.*Dao.*(..))")
	public void after(JoinPoint joinPoint) {
		logger.info(" After executing a method {}", joinPoint);
		logger.info(" Arguments passed are {}", joinPoint.getArgs());
		
	}
	
}