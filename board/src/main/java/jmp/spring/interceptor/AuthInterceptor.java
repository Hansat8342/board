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
				// 로그인 처리 : 세션에 유저 객체를 생성합니다.
				session.setAttribute("user",user);
			}
			
		}
		
		// 로그인 OK
		if(user != null) {
			// USER 권한 체크
			if(user.hasRole("ROLE_USER")) {
				return true;
			}else {
				response.sendRedirect("/login");
				return false;
			}
		}else {
			response.sendRedirect("/login");
			return false;
		}
		
	}

	/**
	 * This implementation is empty.
	 */
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			@Nullable ModelAndView modelAndView) throws Exception {
		
	}
}
