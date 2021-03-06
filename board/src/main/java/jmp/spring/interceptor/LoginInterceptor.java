package jmp.spring.interceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import jmp.spring.service.UserService;
import jmp.spring.vo.User;

public class LoginInterceptor extends HandlerInterceptorAdapter{
	
	@Autowired
	UserService userService;
	/**
	 * This implementation always returns {@code true}.
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		return true;
	}
	
	/**
	 * loginAction 컨트롤러가 실행된 이후에 인터셉터 실행. 자동로그인을 위한 쿠키를 저장 합니다.
	 * 1. 자동로그인을 위한 쿠키 생성.
	 * 2. DB에 세션키 값과 유효기간을 저장.
	 * 만약에 쿠키가 생성되지 않는다면 로직이 실행되는지 로그를 찍어보자.
	 * 인터셉터 클래스가 실행되지 않는다면 설정파일에 등록이 되어 있는지 확인해보자.
	 * 
	 * 저장된 자동 로그인 쿠키(loginCookie)의 value(sessionId)값을
	 * user테이블에서 조회 해서 일치하는 값이 있으면 자동 로그인 처리
	 * 
	 * 세션 / 쿠키 => request
	 * 
	 * 저장후 users테이블에 sessionkey 값이 저장되었는지 확인
	 * 브라우저에 loginCookie가 생성되었는지 확인
	 */
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			@Nullable ModelAndView modelAndView) throws Exception {
		//로그인이 성공 했다면 = 세션에 user객체가 생성되어 있다면
		//세션에 user 객체를 생성하는 시점 : /loginAction
		
		//리퀘스트로부터 세션 구해오기
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		// 조건 - 세션에 유저 객체 생성, 자동로그인 체크
		
		System.out.println("interceptor==============="+user);
		System.out.println("useCookie================="+request.getAttribute("useCookie"));
		//로그인 성공인 경우 자동로그인을 위한 쿠키 생성
		//자동로그인에 체크했을 경우에만
		//사용자의 요청정보 request.getParameter("useCookie") 여기에 담겨있음.
		// StringUtils.isEmpty(request.getParameter("useCookie")); 이렇게 받아올수도 있다. 
		if(user != null && request.getParameter("useCookie") != null) {
			
			//Users 테이블에 쿠키정보를 저장 (자동로그인에 체크를 했을경우에만 저장함)
			// session.getId()를 sessionkey 컬럼에 저장.
			//loginCookie = 자동 로그인시 생성하는 쿠키
			// 여기에 저장되는 value 값과 users테이블의 sessionkey에 저장되는 값은 동일 해야 한다.
			user.setSessionkey(session.getId());
			userService.updateSessionkey(user);
			
			// 자동로그인을 위한 쿠키를 생성합니다.
			Cookie loginCookie = new Cookie("loginCookie", session.getId());
			
			//유효기간과 PATH를 지정합니다
			loginCookie.setMaxAge(60*60*24*7);
			loginCookie.setPath("/");
			
			// 자동로그인을 위해 생성한 쿠키를 response객체에 저장합니다.
			response.addCookie(loginCookie);
			
			// DB에 세션키값과 유효기간 저장
			// loginCookie 의 value 값과 동일한 값 입력
			// 자동 로그인 시 저장된 쿠키값을 DB에서 조회.
			user.setSessionkey(session.getId());
			userService.updateSessionkey(user);
		}
		
		//자동 로그인시 저장된 tmpUri 가 세션에 존재 한다면
		//원래 요청한 페이지가 있었다면 그 요청 페이지로 이동 시켜준다
		String tmpUri = (String) session.getAttribute("tmpUri");
		
		if(!StringUtils.isEmpty(tmpUri)) {
			response.sendRedirect(tmpUri);
		}
	}
}
