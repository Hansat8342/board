package jmp.spring.service;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import jmp.spring.mapper.UserMapper;
import jmp.spring.vo.User;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserMapper mapper;
	
	@Override
	/*
	 * 회원 가입시 암호화된 비밀번호를 입력합니다.
	 */
	public User login(User vo) { //User user 은 화면에서 넘어온 ID, PWD만 가지고 있음.
		//로그인
		User loginUser = mapper.login(vo); //loginUser 은 데이터베이스에서 유저 정보를 가져옴.
		//로그인 성공시 유저의 권한을 조회 해줍니다.
		if(loginUser != null) {
			// 비밀번호를 비교하는 로직 추가
			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
			// 따라서 여기 비밀번호 매치할때 조심해야 한다. 화면에서 넘어온 정보와 데이터베이스의 암호화된 비밀번호를 매치 시켜봐야한다.
			if(!encoder.matches(vo.getPwd(), encoder.encode(loginUser.getPwd()))) {
				// 비밀번호가 틀릴경우 return null반환 = user 객체 null
				return null;
			}
			// 만약 비밀번호가 일치하면 권한조회 후 유저객체 반환
			//유저 권한 조회
			List<String> role = mapper.getRole(vo.getId());
			//유저 객체에 권한 담아주기
			loginUser.setRole(role);
		}
		
		return loginUser;
	}

	@Override
	public int updateSessionkey(User user) {
		
		return mapper.updateSessionkey(user);
	}

	@Override
	public User loginSessionkey(String sessionkey) {
		User user = mapper.loginSessionkey(sessionkey);
		List<String> role = mapper.getRole(user.getId());
		//유저 객체에 권한 담아주기
		user.setRole(role);
		return user;
	}

	@Override
	public int insertUser(User user) {
		//비밀번호 암호화 작업을 추가
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		user.setPwd(encoder.encode(user.getPwd())); //가입시 작성된 pwd를 받아오고, 암호화 시키고, 암호화된 pwd를 설정. 
		//사용자추가
		int res = mapper.insertUser(user);
		//권한 추가
		if(res>0) {
			//사용자 권한 추가
			res = mapper.insertUserRole(user.getId(), "ROLE_USER");
		}
		return res;
	}

	@Override
	public String findId(User user) {
		User loginUser = mapper.login(user);
		
		if(loginUser.getName().equals(user.getName()) && loginUser.getEmail().equals(user.getEmail())) {
			return loginUser.getId();
		}
		return null;
	}




}
