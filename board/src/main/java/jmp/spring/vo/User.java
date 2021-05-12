package jmp.spring.vo;

import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class User {
	
	String id;
	String pwd;
	String name;
	String email;
	// 사용여부
	String enabled;
	// 권한 리스트
	List<String> role;	// 권한 사용자의 권한을 조회 하여 입력 해주세요
	
	// 자동 로그인에 필요한 키값
	String sessionkey;
	// 자동로그인 유효기간
	Date sessionlimit;
	
	/*
	 * 권한이 있는지 체크합니다.
	 * @param role_id
	 * @return
	 */
	public boolean hasRole(String role_id) {
		if(role != null) {
			return role.contains(role_id);			
		}
		return false;
	}
}

