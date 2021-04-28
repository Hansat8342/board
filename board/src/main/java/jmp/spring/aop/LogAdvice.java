package jmp.spring.aop;

import org.aspectj.lang.JoinPoint;
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
	@Before("execution(* jmp.spring.service.BoardService.*(..))") 
	public void logBefore(JoinPoint jp) {
		log.info("AOP=================");
	}

	

	@Around("execution(* jmp.spring.service.BoardService.*(..))")
	public void logTime(ProceedingJoinPoint pjp) {

		log.info("logTime==========================");
		// 타겟 객체의 실행 전, 후의 시간을 측정하여 실행시간을 로그에 기록합니다.
	}

}
