package jmp.spring.board;

import java.util.UUID;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import jmp.spring.mapper.UserMapper;
import jmp.spring.vo.User;

public class PasswordEncode {
	
	@Autowired
	public UserMapper usermapper;
	
	@Test
	public void uuidTest() {
		// 비밀번호 찾기 (입력된 아이디와 이메일이 존재할 경우) -> 임시비밀번호를 email로 전달.
		// 임시비밀번호 생성
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		System.out.println("======================="
							+encoder.encode(UUID.randomUUID().toString().substring(0,7)));
	}
	
	@Test
	public void test() {
		//스프링에서 제공해주는 암호화 방식
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		// 암호화를 하면 항상 문자열이 바뀜.
		System.out.println("================1234의 암호화된 문자열"+encoder.encode("1234"));
		
		//기존의 비밀번호와 일치하는지 확인
		System.out.println("============1234일치 확인"+encoder.matches("1234", encoder.encode("1234")));
	}
	
}
