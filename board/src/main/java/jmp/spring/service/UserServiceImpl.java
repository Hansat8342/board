package jmp.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jmp.spring.mapper.UserMapper;
import jmp.spring.vo.User;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserMapper mapper;
	
	@Override
	public User login(User user) {
		//로그인
		User loginUser = mapper.login(user);
		//로그인 성공시 유저의 권한을 조회 해줍니다.
		if(loginUser != null) {
			//유저 권한 조회
			List<String> role = mapper.getRole(user.getId());
			//유저 객체에 권한 담아주기
			loginUser.setRole(role);
		}
		
		return loginUser;
	}

}
