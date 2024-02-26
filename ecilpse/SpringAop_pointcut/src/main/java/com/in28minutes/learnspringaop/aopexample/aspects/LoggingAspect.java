package com.in28minutes.learnspringaop.aopexample.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

@Configuration
@Aspect
public class LoggingAspect {

	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Before("com.in28minutes.learnspringaop.aopexample.aspects.CommomPointcutConfig.businessPackageConfig()")
	public void logMethodCall(JoinPoint joinPoint) {
		logger.info("Before Aspect - Method is calles - {}", joinPoint);
	}
	
	/* @After	-> 에러 발생해도 실행
	 * @AfterReturning 
	 * @AfterThrowing
	 * @Around -> 실행 전과 후*/
	
	@AfterThrowing(pointcut = "execution(* com.in28minutes.learnspringaop.aopexample.*.*.*(..))",
			throwing = "myExceptionName")
	public void logMethodCalAfterException(JoinPoint joinPoint,Exception myExceptionName) {
		logger.info("AfterThrowing Aspect - {} - {}", joinPoint,myExceptionName);
	}
	
	@AfterReturning(pointcut = "execution(* com.in28minutes.learnspringaop.aopexample.*.*.*(..))",
			returning = "resultValue")
	public void logMethodCalAfterReturning(JoinPoint joinPoint,Object resultValue) {
		logger.info("AfterReturning Aspect - {} - {}", joinPoint,resultValue);
	}
}
