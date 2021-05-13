package jmp.spring.interceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.util.WebUtils;

import jmp.spring.service.UserService;
import jmp.spring.vo.User;

public class AuthInterceptor extends HandlerInterceptorAdapter{

	@Autowired
	UserService userService;
	/**
	 * This implementation always returns {@code true}.
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		
		// 만약 유저객체가 null 이라면  = 로그인 하지 않은 사용자가 접근 했다면
		if(user==null) {
			// 자동 로그인이 가능한 사용자인지 판단
			Cookie loginCookie = WebUtils.getCookie(request, "loginCookie"); // 저장한 쿠키값 가져오기
			if(loginCookie != null) {
				user = userService.loginSessionkey(loginCookie.getValue());
				if(user != null) {					
					// 로그인 처리 : 세션에 유저 객체를 생성합니다.
					session.setAttribute("user",user);
				}
			}
			
		}
		
		// 로그인 OK
		if(user != null) {
			// USER 권한 체크
			if(user.hasRole("ROLE_USER")) {
				// 로그인과 권한이 충족되야만 list 에 접근 가능
				return true;
			}else {
				response.sendRedirect("/login");
				return false;
			}
		}
		
		//만약  로그인을 안햇거나 권한이없다면 로그인 페이지로 이동
		// 원래 요청햇던 페이지와 파라메터를 세션에 저장 >> 세션 삭제
		System.out.println("uri===="+request.getRequestURI());
		System.out.println("query====="+ request.getQueryString());
		String uri = request.getRequestURI(); //기존요청의 uri정보
		String query = request.getQueryString(); //기존 요청의 파라메터
		
		if(query != null) {
			uri += "?" + query;
		}
		session.setAttribute("tmpUri", uri);
		
		// 로그인 안했으면
		response.sendRedirect("/login");
		return false;
		
	}

	/**
	 * This implementation is empty.
	 */
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			@Nullable ModelAndView modelAndView) throws Exception {
		
	}
}
