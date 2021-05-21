package jmp.spring.service;

import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.UUID;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import jmp.spring.mapper.UserMapper;
import jmp.spring.vo.MenuVo;
import jmp.spring.vo.User;

@Service
@Component
public class UserServiceImpl implements UserService {

	@Autowired
	UserMapper mapper;
	
	@Autowired
	Properties prop;
	
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
			// 따라서 여기 비밀번호 매치할때 조심해야 한다.
			// 화면에서 넘어온 정보와 데이터베이스의 암호화된 비밀번호를 매치 시켜봐야한다.
//			if(!encoder.matches(vo.getPwd(), encoder.encode(loginUser.getPwd()))) { 화면에서 받아온 pwd 
			if(!encoder.matches(vo.getPwd(), loginUser.getPwd())) {
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
	public User findId(User user) {
		User loginUser = mapper.login_name(user);
		if(loginUser!=null) {
			if(loginUser.getName().equals(user.getName()) && loginUser.getEmail().equals(user.getEmail())) {
				return loginUser;
				
			}
		}
		return null;
	}
	
	@Override
	public User findPwd(User user) {
		User loginUser = mapper.login(user);
		
		if(loginUser!=null) {
			if(loginUser.getId().equals(user.getId()) && loginUser.getEmail().equals(user.getEmail())) {
				return loginUser;
				
			}
		}
		return null;
	
	}

	@Override
	public String sendEmail(User user) {
		// 메일 설정 정보
				//Properties prop = System.getProperties();
				Properties prop = new Properties();
				prop.put("mail.smtp.ssl.enable", "true"); // 로그인시 TLS를 사용할 것인지 설정
				prop.put("mail.smtp.ssl.trust", "smtp.gmail.com");	
				prop.put("mail.smtp.host", "smtp.gmail.com");// SMTP서버
				prop.put("mail.smtp.auth", "true");// SMTP 서버의 인증 사용
				prop.put("mail.smtp.port", "465");// TLS 포트번호= 587, SSL 포트번호= 465
				
				String mail_id = "junghyunmin9729@gmail.com";
				String mail_pw = "roslcnanzivkhaxo";
				
				// 구글 계정 인증용 ID/PW 세팅
				Authenticator auth = new MailAuth(mail_id, mail_pw);
				// 세션 및 메세지 생성 (프로퍼티, 인증)
				Session session = Session.getDefaultInstance(prop, auth);
				MimeMessage msg = new MimeMessage(session);

				String pw = UUID.randomUUID().toString().substring(0,7);
				
				try {
					// 보내는 날짜 지정
					msg.setSentDate(new Date());
					// 발송자 설정 (발송자의 메일, 발송자명)
					msg.setFrom(new InternetAddress("junghyunmin9729@gmail.com", "정현민"));
		             // 수신자 설정 
					// Message.RecipientType.TO : 받는 사람 
					InternetAddress to = new InternetAddress("junghyunmin9729@gmail.com");
					msg.setRecipient(Message.RecipientType.TO, to);
					
					
					
		            // 메일 제목
					msg.setSubject("비밀번호 찾기의 결과 입니다.", "UTF-8");
					// 메일 내용
					msg.setText("비밀번호 찾기 결과 입니다.\n"+user.getName()+"님의 비밀번호는 "+ pw +"\n다음부터는 조심해 주세요.", "UTF-8");
					
		            // 메일 발송
					Transport.send(msg);

				} catch (AddressException ae) {// 주소를 입력하지 않았을 경우
					System.out.println("AddressException : " + ae.getMessage());
				} catch (MessagingException me) {// 메세지에 이상이 있을 경우
					System.out.println("MessagingException : " + me.getMessage());
				} catch (UnsupportedEncodingException e) {
					System.out.println("UnsupportedEncodingException : " + e.getMessage());
				}
				return pw;
	}

	@Override
	public int updatePwd(User user) {
		
		return mapper.updatePwd(user);
	}

	@Override
	public User checkId(String id) {
		
		return mapper.checkId(id);
	}

	@Override
	public List<MenuVo> getMenu() {
		return mapper.getMenu();
	}




}
