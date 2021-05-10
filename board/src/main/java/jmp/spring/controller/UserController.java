package jmp.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import jmp.spring.service.UserService;
import jmp.spring.vo.User;

@Controller
public class UserController {

	@Autowired
	public UserService service;
	
	@GetMapping("/login")
	public void login() {
		
	}
	
	@PostMapping("/loginAction")
	public String loginAction(User vo, Model model) {
		
		User user = service.login(vo);
		
		if(user == null) {
			model.addAttribute("msg", "로그인에 실패했습니다. \nID/PW를 확인하세요.");
			return "/login";
		}else {
			model.addAttribute("msg", user.getId() + "님 환영합니다.");
			return "/loginAction";
		}
	}
	
}
