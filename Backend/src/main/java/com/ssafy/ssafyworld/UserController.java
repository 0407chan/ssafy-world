package com.ssafy.ssafyworld;

import java.util.List;

import javax.inject.Inject;

import org.mindrot.jbcrypt.BCrypt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.ssafyworld.dto.RoomDTO;
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
		logger.info("전체 유저 출력");
		return new ResponseEntity<List<UserDTO>>(uService.selectUsers(), HttpStatus.OK);
	}
	/**
	 * 10-29 : 이규찬
	 *
	 * @기능 유저 아이디를 통한 유저 정보
	 * @호출방법 ssafywolrd/user/info
	 * @param uid
	 * @return UserDTO
	 */
	@RequestMapping(value = "/user/info/{uidx}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<UserDTO> getUserInfo(@PathVariable("uidx") int uidx) throws Exception{
		try {
			logger.info("유저 정보 출력");
			return ResponseEntity.ok().body(uService.getUserInfo(uidx));
		}
		catch (Exception e) {
			logger.info("유저 정보 출력 에러");
			return ResponseEntity.badRequest().body(null);
		}
	}
	/**
	 * 2019.10.25 이찬호
	 *
	 * @기능 회원가입
	 * @호출방법 ssafywolrd/user/register
	 * @param UserDTO User
	 * @return 성공시 200 OK 실패시 400 BAD_REQUEST
	 * @추가 비밀번호 암호화 해서 저장 추가
	 */
	@RequestMapping(value = "/user/register", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<String> register(@RequestBody UserDTO user) throws Exception {
		UserDTO resultUser = uService.getUser(user);
		if (resultUser != null) {
			logger.error("이미 존재하는 유저");
			return ResponseEntity.badRequest().body("User already Exist");
		}
		String hashPw = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
		user.setPassword(hashPw);
		uService.register(user);
		logger.info("유저 생성 완료");

		return ResponseEntity.ok().body("User Created");
	}

	/**
	 * 2019.10.23 이찬호 -> 추후 AWT로 토큰 받아와야함
	 *
	 * @기능 로그인
	 * @호출방법 ssafywolrd/user/login
	 * @param uid, password
	 * @return 성공 200 OK , 실패 400 BAD REQUEST
	 * @Test - 아이디가 있는 경우엔 어떻게 되는가?
	 */
	@RequestMapping(value = "/user/login", method = RequestMethod.POST , produces="application/json; charset=utf8")
	@ResponseBody
	public ResponseEntity<String> login(@RequestBody UserDTO user) throws Exception {
		UserDTO resultUser = uService.getUser(user);
		if (resultUser == null) {
			logger.error("없는 유저");
			ResponseEntity.badRequest().body("");
			return ResponseEntity.badRequest().body("존재하지 않는 아이디 입니다.");
		}
		if (!BCrypt.checkpw(user.getPassword(), resultUser.getPassword())) {
			logger.error("로그인 실패");
			return ResponseEntity.badRequest().body("비밀번호가 일치하지 않습니다.");
		}
		logger.info("로그인 성공");
		return ResponseEntity.ok().body(uService.getUser(user).toString());
	}
	
	/**
	 * 10-29 : 이규찬
	 *
	 * @기능 유저 전체 리스트를 가져옴
	 * @호출방법 ssafywolrd/user/rooms
	 * @param UserDTO
	 * @return List<RoomDTO> 
	 */
	@RequestMapping(value = "/user/rooms", method = RequestMethod.POST , produces="application/json; charset=utf8")
	@ResponseBody
	public ResponseEntity<List<RoomDTO>> selectUserRooms(@RequestBody UserDTO user) throws Exception {
		List<RoomDTO> list = uService.selectUserRooms(user.getUid());
		logger.info("유저가 들어간 방 리스트 출력");
		try {
			return ResponseEntity.ok().body(list);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(null);
		}
	}
}
