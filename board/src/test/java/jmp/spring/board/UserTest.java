package jmp.spring.board;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import jmp.spring.mapper.UserMapper;
import jmp.spring.vo.User;
import lombok.extern.log4j.Log4j;

@Log4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class UserTest {
	@Autowired
	public UserMapper usermapper;
	
	@Test
	public void userTest() {
		User user = new User();
		user.setId("user023");
		user.setPwd("1234");
		usermapper.login(user);
		log.info("====================="+usermapper.login(user));
	}
}
