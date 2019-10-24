package com.ssafy.ssafyworld;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.ssafyworld.dto.UserDTO;
import com.ssafy.ssafyworld.service.UserService;

/**
 * Handles requests for the application home page.
 */
@CrossOrigin(origins="*")
@RestController
public class UserController {
	
	@Inject
    private UserService uService;
	
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	
	/**
	 * 10-15 : 최재형 
	 * @기능 유저 전체 리스트를 가져옴
	 * @호출방법 ssafywolrd/user
	 * @param X
	 * @return List<UserDTO> user 데이터
	 */
	@RequestMapping(value = "/user", method = RequestMethod.GET)
	@ResponseBody
	public List<UserDTO> selectUsers() throws Exception {
		logger.info("Welcome home! The client locale is {}.");
		System.out.println("유저 데이터 호출 완료");
		return uService.selectUsers();
	}
	
	/**
	 * 10-18 : 박규빈 -> 추후 params로 로그인 가능해야함
	 * @기능 회원가입
	 * @호출방법 ssafywolrd/user/register
	 * @param UserDTO User
	 * @return X
	 */
	@RequestMapping(value = "/user/register", method = RequestMethod.POST)
	@ResponseBody
	public void register(@RequestBody UserDTO user) throws Exception {
		System.out.println("회원가입"+user);
		uService.register(user);
	}
	
	/**
	 * 2019.10.23 이찬호  -> 추후 AWT로 토큰 받아와야함
	 * @기능 로그인
	 * @호출방법 ssafywolrd/user/login
	 * @param uid, password
	 * @return int?
	 * @Test 
	 * 		- 아이디가 있는 경우엔 어떻게 되는가?
	 */
	@RequestMapping(value = "/user/login", method = RequestMethod.POST)
	@ResponseBody
	public UserDTO login(@RequestBody UserDTO user) throws Exception {
		System.out.println("UserController, login : uid=["+user.getUid()+"] password=["+user.getPassword()+"]");
		UserDTO resultUser = uService.getUser(user);
		
		if(resultUser == null) {
			return null;
		}
		
		return resultUser;
	}
	
}
