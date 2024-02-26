package com.in28minutes.learnspringaop.aopexample.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

@Aspect
@Configuration
public class PerformanceTrackingAspect {
	
	/*	메소드 실행 시간 알아보
	 * ProceedingJoinPoint -> excute Method too*/
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
//	@Around("execution(* com.in28minutes.learnspringaop.aopexample.*.*.*(..))")
	//@TrackTime어노테이션이 붙은 메소드에 실 
	@Around("com.in28minutes.learnspringaop.aopexample.aspects.CommomPointcutConfig.trackTimeAnnotation()")
	public Object findExecutionTime(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
		//Start a timer
		Long startTimeMillis = System.currentTimeMillis();
		
		//Excute the method
		Object returnValue = proceedingJoinPoint.proceed();	//메소드 실핼 후 결과값 반
		
		//Stop the timer
		Long stopTimeMillis = System.currentTimeMillis();
		
		Long executionDuration = stopTimeMillis - startTimeMillis;
		
		logger.info("Around Aspect - {} - {} ms",proceedingJoinPoint,executionDuration);	//proceedingJoinPoint -> 실행 메소드명
		
		return returnValue;
	}
	
}
