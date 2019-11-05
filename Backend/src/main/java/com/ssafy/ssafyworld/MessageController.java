package com.ssafy.ssafyworld;

import java.util.List;

import javax.inject.Inject;

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

import com.ssafy.ssafyworld.dto.MessageDTO;
import com.ssafy.ssafyworld.dto.imgDTO;
import com.ssafy.ssafyworld.service.MessageService;

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
	@RequestMapping(value = "/message", method = RequestMethod.GET, produces="application/json; charset=utf8")
	@ResponseBody
	public ResponseEntity<List<MessageDTO>> selectMessages() throws Exception {
		logger.info("전체 메세지 출력");
		return new ResponseEntity<List<MessageDTO>>(mService.selectMessages(),HttpStatus.OK);
	}
	
	/**
	 * 10-21 : 박규빈 
	 * @기능 해당 방의 메세지 모두 가져오기
	 * @호출방법 ssafywolrd/message/{ridx}
	 * @param ridx
	 * @return List<MessageDTO>
	 */
	@RequestMapping(value = "/message/{ridx}", method = RequestMethod.GET, produces="application/json; charset=utf8")
	@ResponseBody
	public ResponseEntity<List<MessageDTO>> roomMessages(@PathVariable("ridx") int ridx) throws Exception {
		logger.info(ridx+"번 방 메세지 추출 완료");
		return new ResponseEntity<List<MessageDTO>>(mService.roomMessages(ridx),HttpStatus.OK);
	}
	
	/**
	 * 10-28 : 박규빈 
	 * @기능 해당 방에
	 * @호출방법 ssafywolrd/message
	 * @param rixd, uidx , text , time
	 * @return 200 OK
	 */
	@RequestMapping(value = "/message", method = RequestMethod.POST, produces="application/json; charset=utf8")
	@ResponseBody
	public ResponseEntity<String> insertMessage(@RequestBody MessageDTO message) throws Exception {
		mService.insertMessage(message);
		System.out.println("메시지 입력 : "+message.getText());
		return new ResponseEntity<String>("메세지 입력 완료",HttpStatus.OK);
	}
	/**
	 * 11-05 : 이규찬 
	 * @기능 사진입력
	 * @호출방법 ssafywolrd/message/img
	 * @param ridx , img
	 * @return 200 OK
	 */
	@RequestMapping(value = "/message/img", method = RequestMethod.POST, produces="application/json; charset=utf8")
	@ResponseBody
	public ResponseEntity<String> insertImg(@RequestBody imgDTO img) throws Exception {
		System.out.println("테스트"+img);
		mService.insertImg(img);
		System.out.println("img : "+img.getImg());
		return new ResponseEntity<String>("이미지 입력 완료",HttpStatus.OK);
	}
	/**
	 * 11-05 : 이규찬 
	 * @기능 해당 방의 이미지 모두 가져오기
	 * @호출방법 ssafywolrd/mesage/img/{ridx}
	 * @param ridx
	 * @return List<imgDTO>
	 */
	@RequestMapping(value = "/message/getImg/{ridx}", method = RequestMethod.GET, produces="application/json; charset=utf8")
	@ResponseBody
	public ResponseEntity<List<imgDTO>> roomImgs(@PathVariable("ridx") int ridx) throws Exception {
		logger.info(ridx+"번 방 이미지 추출 완료");
		return new ResponseEntity<List<imgDTO>>(mService.roomImgs(ridx),HttpStatus.OK);
	}
	
}
