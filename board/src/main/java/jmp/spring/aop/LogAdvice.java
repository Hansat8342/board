package jmp.spring.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import lombok.extern.log4j.Log4j;

@Aspect
@Component
@Log4j
public class LogAdvice {

			//execution(수식어(생략가능 public, private..) 리턴타입 클래스이름(생략가능, 쓸경우 풀패키지명) 매서드이름(파라미터)
//	@Before("execution(* jmp.spring.service.BoardService.*(..))") 
//	public void logBefore() {
//		log.info("AOP=================");
//	}
//
//	
//
//	@Around("execution(* jmp.spring.service.BoardService.*(..))")
//	public Object logTime(ProceedingJoinPoint pjp) {
//			
//			// @before  메소드 실행전 시간
//			long startTime = System.currentTimeMillis();
//			
//			log.info("============"+pjp.getTarget());
//			Object res = null;
//			try {
//				res = pjp.proceed();
//			} catch (Throwable e) {
//				// @AfterThrowing
//				e.printStackTrace();
//			}
//			
//			long endTime = System.currentTimeMillis();
//			// @after  메소드 실행 후 시간
//			// 메소드의 실행 시간을 측정하여 로그로 남겨줍니다.
//			log.info("============ 실행시간 : " + (endTime-startTime));
//	
//			return res;
//		}

}
