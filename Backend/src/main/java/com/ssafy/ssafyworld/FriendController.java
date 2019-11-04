package com.ssafy.ssafyworld;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.ssafyworld.dto.FriendDTO;
import com.ssafy.ssafyworld.service.FriendService;

/**
 * Handles requests for the application home page.
 */
@CrossOrigin(origins="*")
@RestController
public class FriendController {
	
	@Inject
	private FriendService fService;
	
	private static final Logger logger = LoggerFactory.getLogger(FriendController.class);


	/**
	 * 10-28 : 박규빈 
	 * @기능 전체 친구
	 * @호출방법 ssafywolrd/friend/
	 * @param X
	 * @return List<FriendDTO>
	 */
	@RequestMapping(value = "/friend", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<List<FriendDTO>> selectFriends() throws Exception {
		logger.info("전체 친구 출력");
		return ResponseEntity.ok().body(fService.selectFriends());
	}

	/**
	 * 10-28 : 박규빈 
	 * @기능 uid 해당하는 친구 리스트
	 * @호출방법 ssafywolrd/friend
	 * @param uidx
	 * @return List<FriendDTO>
	 */
	@RequestMapping(value = "/friend/{uidx}", method = RequestMethod.GET ,produces="application/json; charset=utf8" )
	@ResponseBody
	public ResponseEntity<List<String>> selectFriend(@PathVariable("uidx") int uidx) throws Exception {
		logger.info("UID 해당 친구 출력");
		System.out.println(uidx+"의 친구 출력");
		return ResponseEntity.ok().body(fService.selectFriend(uidx));
	}
	
	/**
	 * 10-28 : 박규빈 
	 * @기능 친구삭제
	 * @호출방법 ssafywolrd/friend/delete
	 * @param FriendDTO
	 * @return 200 OK 400 BAD REQUEST
	 * @throws Exception 
	 */
	@RequestMapping(value="/friend/delete", method=RequestMethod.POST, produces="application/json; charset=utf8")
	@ResponseBody
	public ResponseEntity<String> deleteFriend(@RequestBody FriendDTO friend) throws Exception{
		int person1 = friend.getUidx();
		int person2 = friend.getFriend();
		FriendDTO temp = new FriendDTO(0,person2,person1);
		System.out.println(temp);
		try {
			fService.deleteFriend(friend);
			fService.deleteFriend(temp);
			logger.info("친구 삭제 완료");
			return ResponseEntity.ok().body("친구 삭제 완료");
		} catch (Exception e) {
			logger.info("친구 삭제 실패");
			return ResponseEntity.badRequest().body("친구 삭제 실패");
		}
	}
	
	/**
	 * 10-28 : 박규빈 
	 * @기능 친구추가
	 * @호출방법 ssafywolrd/friend/add
	 * @param friend
	 * @return 200 OK 400 BAD REQUEST
	 * @throws Exception 
	 */
	@RequestMapping(value="/friend/add", method=RequestMethod.POST, produces="application/json; charset=utf8")
	@ResponseBody
	public ResponseEntity<String> addFriend(@RequestBody FriendDTO friend) throws Exception{
		int person1 = friend.getUidx();
		int person2 = friend.getFriend();
		FriendDTO temp = new FriendDTO(0,person2,person1);
		System.out.println(temp);
		try {
			fService.addFriend(friend);
			fService.addFriend(temp);
			logger.info("친구 추가 완료");
			return ResponseEntity.ok().body("친구 추가 완료");
		} catch (Exception e) {
			logger.info("친구 추가 실패");
			return ResponseEntity.badRequest().body("친구 추가 실패");
		}
	}
	
}
