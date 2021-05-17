package jmp.spring.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import jmp.spring.vo.User;

public interface UserMapper {
	public User login(User user);

	public User login_name(User user);
	
	public List<String> getRole(String id);
	
	/*
	 * 자동록인을 위한 키값과 유효기간을 저장합니다.
	 * -로그인 사용자의 아이디
	 * -세션 ID
	 */
	public int updateSessionkey(User user);
	
	public User loginSessionkey(String sessionkey);
	
	/*
	 * 회원 가입 처리
	 */
	public int insertUser(User user);
	
	/**
	 * 회원 권한 등록
	 * 
	 * @param id
	 * @param role
	 * @return
	 */
	public int insertUserRole(@Param("id") String id,
								@Param("role") String role);
	
	public User findId(User user);
	
	public User findPwd(User user);
}
