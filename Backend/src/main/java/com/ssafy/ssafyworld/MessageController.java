package com.ssafy.ssafyworld;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.ssafyworld.dto.MessageDTO;
import com.ssafy.ssafyworld.dto.RoomDTO;
import com.ssafy.ssafyworld.dto.UserDTO;
import com.ssafy.ssafyworld.service.MessageService;
import com.ssafy.ssafyworld.service.RoomService;
import com.ssafy.ssafyworld.service.UserService;

/**
 * Handles requests for the application home page.
 */
@CrossOrigin(origins="*")
@RestController
public class MessageController {
	
	@Inject
	private MessageService mService;
	
	private static final Logger logger = LoggerFactory.getLogger(MessageController.class);


	/**
	 * 10-21 : 박규빈 
	 * @기능 전체 메세지
	 * @호출방법 ssafywolrd/message/
	 * @param X
	 * @return List<MessageDTO>
	 */
	@RequestMapping(value = "/message", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<List<MessageDTO>> selectMessages() throws Exception {
		logger.info("전체 메세지 출력");
		return new ResponseEntity<List<MessageDTO>>(mService.selectMessages(),HttpStatus.OK);
	}
	
	/**
	 * 10-21 : 박규빈 
	 * @기능 해당 방의 메세지 모두 가져오기
	 * @호출방법 ssafywolrd/message/{rid}
	 * @param rid
	 * @return List<MessageDTO>
	 */
	@RequestMapping(value = "/message/{rid}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<List<MessageDTO>> roomMessages(@PathVariable("rid") int rid) throws Exception {
		logger.info(rid+"번 방 메세지 추출 완료");
		return new ResponseEntity<List<MessageDTO>>(mService.roomMessages(rid),HttpStatus.OK);
	}
	
}
