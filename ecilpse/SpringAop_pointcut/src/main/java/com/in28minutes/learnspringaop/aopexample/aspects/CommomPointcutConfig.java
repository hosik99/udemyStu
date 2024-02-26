package com.in28minutes.learnspringaop.aopexample.aspects;

import org.aspectj.lang.annotation.Pointcut;

public class CommomPointcutConfig {

	//포인트컷을 지정 및 변수지정 -> 나중에  이곳의  변수만 바꾸면 
	@Pointcut("execution(* com.in28minutes.learnspringaop.aopexample.*.*.*(..))")
	public void businessPackageConfig() {}
	
	@Pointcut("execution(* com.in28minutes.learnspringaop.aopexample.data.*.*(..))")
	public void dataPackageConfig2() {}
	
	//package이름에 'Service'이 들어가면 ->DataService, BusinessService
	@Pointcut("bean(*Service*)")
	public void dataPackageConfigUsingBean() {}
	
	//어노테이션을 포인트컷으
	@Pointcut("@annotation(com.in28minutes.learnspringaop.aopexample.annotations.TrackTime)")
	public void trackTimeAnnotation() {}
}
