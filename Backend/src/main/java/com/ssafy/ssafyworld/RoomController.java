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

import com.ssafy.ssafyworld.dto.RoomDTO;
import com.ssafy.ssafyworld.service.MessageService;
import com.ssafy.ssafyworld.service.RoomService;

/**
 * Handles requests for the application home page.
 */
@CrossOrigin(origins="*")
@RestController
public class RoomController {
	
	@Inject
	private RoomService rService;
	private MessageService mService;
	
	private static final Logger logger = LoggerFactory.getLogger(RoomController.class);

	/**
	 * 10-28 : 박규빈 			 
	 * @기능 로그인
	 * @호출방법 ssafywolrd/room
	 * @param X
	 * @return List<RoomDTO>
	 */

	@RequestMapping(value = "/room", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<List<RoomDTO>> selectRooms() throws Exception {
		logger.info("전체 방 출력");
		return new ResponseEntity<List<RoomDTO>>(rService.selectRooms(),HttpStatus.OK);
	}
	
	/**
	 * 10-18 : 박규빈 			10-24 수정 : 이규찬 
	 * @기능 로그인
	 * @호출방법 ssafywolrd/room/{rid}
	 * @param rid
	 * @return RoomDTO
	 */

	@RequestMapping(value = "/room/{rid}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<RoomDTO>selectRoom(@PathVariable("rid") int rid) throws Exception {
		logger.info(rid+"방 선택 완료");
		return new ResponseEntity<RoomDTO>(rService.selectRoom(rid),HttpStatus.OK);
	}
	
	/**
	 * 10-23 : 이규찬 
	 * @기능 방생성
	 * @호출방법 ssafywolrd/room/create
	 * @param rname
	 * @return Integer
	 * @throws Exception 
	 */
	@RequestMapping(value="/room/create", method=RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<Integer> createRoom(@RequestBody RoomDTO room) throws Exception {
		logger.info(room.getRname()+" 방 생성");
		return new ResponseEntity<Integer>(rService.createRoom(room.getRname()),HttpStatus.OK);
	}
	
	/**
	 * 10-24 : 이규찬 
	 * @기능 방삭제
	 * @호출방법 ssafywolrd/room/delete
	 * @param rid
	 * @return Integer
	 * @throws Exception 
	 */
	@RequestMapping(value="/room/delete", method=RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<String> deleteRoom(@RequestBody RoomDTO room) throws Exception{
		try {
			rService.deleteRoom(room.getRid());
			logger.info(room.getRid()+"번 방 삭제");
			return new ResponseEntity<String>("Room Delete",HttpStatus.OK);
		} catch (Exception e) {
			logger.error("방 삭제 실패");
			return new ResponseEntity<String>("Room Delete Fail",HttpStatus.BAD_REQUEST);
		}
	}
}
