package jmp.spring.service;

import jmp.spring.vo.User;

public interface UserService {

	public User login(User user);
	
	/*
	 * 자동록인을 위한 키값과 유효기간을 저장합니다.
	 * -로그인 사용자의 아이디
	 * -세션 ID
	 */
	public int updateSessionkey(User user);
	
	public User loginSessionkey(String sessionkey);
}
