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

import com.ssafy.ssafyworld.dto.FriendDTO;
import com.ssafy.ssafyworld.dto.RoomDTO;
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
	 * @return List<MessageDTO>
	 */
	@RequestMapping(value = "/friend", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<List<FriendDTO>> selectFriends() throws Exception {
		logger.info("전체 친구 출력");
		return new ResponseEntity<List<FriendDTO>>(fService.selectFriends(),HttpStatus.OK);
	}
	
	/**
	 * 10-24 : 이규찬 
	 * @기능 방삭제
	 * @호출방법 ssafywolrd/friend/delete
	 * @param friend
	 * @return X
	 * @throws Exception 
	 */
	@RequestMapping(value="/friend/delete", method=RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<String> deleteFriend(@RequestBody FriendDTO friend) throws Exception{
		try {
			fService.deleteFriend(friend);
			logger.info("친구 삭제 완료");
			return new ResponseEntity<String>("Friend Delete",HttpStatus.OK);
		} catch (Exception e) {
			logger.info("친구 삭제 완료");
			return new ResponseEntity<String>("Friend Delete Fail",HttpStatus.BAD_REQUEST);
		}
	}
}
