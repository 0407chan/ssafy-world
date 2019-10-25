package com.ssafy.ssafyworld;

import java.util.List;

import javax.inject.Inject;

import org.mindrot.jbcrypt.BCrypt;
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
	 * 2019.10.25 이찬호
	 *
	 * @기능 회원가입
	 * @호출방법 ssafywolrd/user/register
	 * @param UserDTO User
	 * @return 성공시 201 CREATED 실패시 400 BAD_REQUEST
	 * @추가 비밀번호 암호화 해서 저장 추가
	 */
	@RequestMapping(value = "/user/register", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<String> register(@RequestBody UserDTO user) throws Exception {
		UserDTO resultUser = uService.getUser(user);
		if (resultUser != null) {
			return ResponseEntity.badRequest().body("User already Exist");
		}
		String hashPw = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
		user.setPassword(hashPw);
		int n = uService.register(user);
		if (n > 0) {
			return ResponseEntity.ok().body("USER CREATED");
		} else {
			return ResponseEntity.badRequest().body("User already Exist");
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

		UserDTO resultUser = uService.getUser(user);
		if (resultUser == null) {
			return ResponseEntity.badRequest().body("User Not Found");
		}
		if(!BCrypt.checkpw(user.getPassword(), resultUser.getPassword())) {
			return ResponseEntity.badRequest().body("Wrong Password");
		}
		return ResponseEntity.ok().body("LOGIN SUCCESS");
	}

}
