package com.ssafy.ssafyworld;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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
@CrossOrigin(origins = "*")
@RestController
public class UserController {

	@Inject
	private UserService uService;

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	/**
	 * 10-24 : 박규빈
	 *
	 * @기능 유저 전체 리스트를 가져옴
	 * @호출방법 ssafywolrd/user
	 * @param X
	 * @return List<UserDTO> user 데이터
	 */
	@RequestMapping(value = "/user", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<List<UserDTO>> selectUsers() throws Exception {
		logger.info("Welcome home! The client locale is {}.");
		System.out.println("유저 데이터 호출 완료");
		List<UserDTO> list = uService.selectUsers();
		return new ResponseEntity<List<UserDTO>>(list, HttpStatus.OK);
	}

	/**
	 * 10-18 : 박규빈
	 *
	 * @기능 회원가입
	 * @호출방법 ssafywolrd/user/register
	 * @param UserDTO User
	 * @return 성공시 201 CREATED 실패시 400 BAD_REQUEST
	 */
	@RequestMapping(value = "/user/register", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<String> register(@RequestBody UserDTO user) throws Exception {
		System.out.println("회원가입" + user);
		int n = uService.register(user);
		if (n > 0) {
			return new ResponseEntity<String>("USER CREATED!!", HttpStatus.CREATED);
		} else {
			return new ResponseEntity<String>("UNIQUE ERROR!!", HttpStatus.BAD_REQUEST);
		}
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
	public ResponseEntity<String> login(@RequestBody UserDTO user) throws Exception {
		System.out.println(user);
		UserDTO resultUser = uService.login(user);
		System.out.println(resultUser);
		if (resultUser == null) {
			return new ResponseEntity<String>("LOGIN ERROR!!", HttpStatus.BAD_REQUEST);
		}
		resultUser = uService.getUser(user);
		return new ResponseEntity<String>("LOGIN  SUCCESS!!", HttpStatus.OK);

	}

}
