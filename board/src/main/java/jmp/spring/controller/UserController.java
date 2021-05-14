package jmp.spring.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.util.WebUtils;

import jmp.spring.service.UserService;
import jmp.spring.vo.User;
import lombok.extern.log4j.Log4j;

@Controller
@Log4j
public class UserController {

	@Autowired
	public UserService service;
	
	@GetMapping("/login")
	public void login() {
		
	}
	
	@GetMapping("/logout")
	public String logout(HttpServletRequest req, HttpServletResponse res) {
		HttpSession session = req.getSession();
		session.invalidate();
		
		// 자동로그인 쿠키를 제거해줍니다.
		// 로그아웃을 하게 되면  더이상 자동로그인을 할 수 가 없습니다.
		Cookie loginCookie = WebUtils.getCookie(req, "loginCookie");
		if(loginCookie != null) {
			loginCookie.setMaxAge(0);
			loginCookie.setPath("/");
			
			res.addCookie(loginCookie);
		}
		return "/login";
	}
	
	@PostMapping("/loginAction")
	public String loginAction(User vo, Model model, HttpServletRequest req) {
		
		User user = service.login(vo);
		
		if(user == null) {
			model.addAttribute("msg", "로그인에 실패했습니다. ID/PW를 확인하세요.");
			return "/login";
		}else {
			//user 객체를 세션에 담아줍니다. - 로그인 처리
			HttpSession session = req.getSession();
			session.setAttribute("user", user);
			
			log.info("======================user" + user);
			
			model.addAttribute("user",user);
			model.addAttribute("msg", user.getId() + "님 환영합니다.");
			return "/loginAction";
		}
	}
	
	@GetMapping("/member")
	public void member() {
		
	}
	
	@PostMapping("/registerMemeber")
	public String registerMember(User user) {
		//회원가입 처리
		try {
			//오류페이지 이동
			//회원가입 성공했으면 /loginAction forward
			int res = service.insertUser(user);
			if(res>0) {
				return "forward:/loginAction";
			}else {
				return "/error";
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "/error";
		}

	}
	
	@GetMapping("/idSearch")
	public void idSearch() {
		
	}
	
	@PostMapping("/idSearch")
	public String idSearch(User user) {
		String res = service.findId(user);
		return res;
			
	}
	
	
}
