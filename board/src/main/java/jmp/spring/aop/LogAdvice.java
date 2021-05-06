package jmp.spring.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import lombok.extern.log4j.Log4j;

@Aspect
@Component
@Log4j
public class LogAdvice {
//	
//	@Around("execution(* jmp.spring.service.*.*(..))")
//	public Object logTime(ProceedingJoinPoint pjp) {
//		// 타겟 메소드의 실행전 시간과 실행후 시간을 구하여 
//		// 메소드의 실행 시간을 측정합니다.
//		Object res = null;
//		// 전처리
//		long startTime = System.currentTimeMillis();
//		// 타겟 객체의 실행!!!!
//		try {
//			res = pjp.proceed();
//		} catch (Throwable e) {
//			// 에러처리
//			// mapper.setLog
//			e.printStackTrace();
//		}
//		
//		// 후처리
//		long endTime =System.currentTimeMillis();
//		log.info("==========="+(endTime-startTime));
//		// 타겟 객체의 결과를 반환 합니다!!
//		return res;
//	}
}












