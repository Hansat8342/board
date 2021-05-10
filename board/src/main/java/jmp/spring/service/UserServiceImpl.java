package jmp.spring.service;

import org.springframework.beans.factory.annotation.Autowired;

import jmp.spring.mapper.UserMapper;
import jmp.spring.vo.User;

public class UserServiceImpl implements UserService {

	@Autowired
	UserMapper mapper;
	
	@Override
	public User login(User user) {
		return mapper.login(user);
	}

}
