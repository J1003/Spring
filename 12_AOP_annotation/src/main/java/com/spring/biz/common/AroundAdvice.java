package com.spring.biz.common;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Service;

@Aspect
@Service
public class AroundAdvice {
	
	//포인트컷 작성 : 명칭은 메소드명 사용
	@Pointcut("execution(* com.spring.biz..*Impl.*(..))")
	public void allPointcut() {}
	
	@Pointcut("execution(* com.spring.biz..*Impl.get*(..))")
	public void getPointcut() {}
	
	@Around("getPointcut()")
	public Object aroundLog(ProceedingJoinPoint pjp) throws Throwable {
		//핵심 메소드 실행전
		System.out.println("[Around BEFORE] 비즈니스 로직 실행전 처리");
		
		Object returnObj = pjp.proceed(); //조인포인트 메소드 실행
		
		//핵심 메소드 실행후
		System.out.println("[Around AFTER] 비즈니스 로직 실행후 처리");
		
		return returnObj;
	}
}
