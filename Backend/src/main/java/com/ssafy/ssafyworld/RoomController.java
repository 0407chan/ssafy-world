package com.ssafy.ssafyworld;

import java.util.ArrayList;
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

import com.ssafy.ssafyworld.dto.RoomDTO;
import com.ssafy.ssafyworld.dto.RoomHasUserDTO;
import com.ssafy.ssafyworld.dto.UserDTO;
import com.ssafy.ssafyworld.service.MessageService;
import com.ssafy.ssafyworld.service.RoomService;
import com.ssafy.ssafyworld.service.UserService;

/**
 * Handles requests for the application home page.
 */
@CrossOrigin(origins="*")
@RestController
public class RoomController {
	
	@Inject
	private RoomService rService;
	
	@Inject
	private UserService uService;
	
	@Inject
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
		return ResponseEntity.ok().body(rService.selectRooms());
	}
	
	/**
	 * 10-18 : 박규빈 			10-24 수정 : 이규찬 
	 * @기능 로그인
	 * @호출방법 ssafywolrd/room/{ridx}
	 * @param rid
	 * @return RoomDTO
	 */
	@RequestMapping(value = "/room/people/{ridx}", method = RequestMethod.GET, produces="application/json; charset=utf8")
	@ResponseBody
	public ResponseEntity<List<UserDTO>>selectRoom(@PathVariable("ridx") int ridx) throws Exception {
		logger.info(ridx+"방에 속한 사람");
		List<UserDTO> temp = rService.selectRoom(ridx);
		List<UserDTO> res = new ArrayList<UserDTO>();
		for(int i=0; i<temp.size(); i++) {
			res.add(uService.getUserInfo(temp.get(i).getUidx()));
		}
		return ResponseEntity.ok().body(res);
	}
	
	/* 2019.11.05 이찬호
	 * @기능 ridx로 room 정보 가져옴
	 * */
	@RequestMapping(value = "/room/{ridx}", method = RequestMethod.GET, produces="application/json; charset=utf8")
	@ResponseBody
	public ResponseEntity<RoomDTO>getRoom(@PathVariable("ridx") int ridx) throws Exception {
		logger.info(ridx+"방을 가져온다");
		return ResponseEntity.ok().body(rService.getRoom(ridx));
	}
	
	
	/**
	 * 10-23 : 이규찬 
	 * @기능 방생성
	 * @호출방법 ssafywolrd/room/create
	 * @param rname
	 * @return Integer
	 * @throws Exception 
	 */
	@RequestMapping(value="/room/create", method=RequestMethod.POST, produces="application/json; charset=utf8")
	@ResponseBody
	public ResponseEntity<Integer> createRoom(@RequestBody RoomDTO room) throws Exception {
		logger.info(room.getRname()+" 방 생성");
		System.out.println(room);
		return ResponseEntity.ok().body(rService.createRoom(room.getRname()));
	}
	
	/**
	 * 10-24 : 이규찬 
	 * @기능 방삭제
	 * @호출방법 ssafywolrd/room/delete
	 * @param rid
	 * @return Integer
	 * @throws Exception 
	 */
	@RequestMapping(value="/room/delete", method=RequestMethod.POST, produces="application/json; charset=utf8")
	@ResponseBody
	public ResponseEntity<String> deleteRoom(@RequestBody RoomDTO room) throws Exception{
		try {
			rService.deleteRoom(room.getRidx());
			logger.info(room.getRidx()+"번 방 삭제");
			return ResponseEntity.ok().body("방 삭제 완료");
		} catch (Exception e) {
			logger.error("방 삭제 실패");
			return ResponseEntity.badRequest().body("방 삭제 실패");
		}
	}
	/**
	 * 10-31 : 이규찬 
	 * @기능 방입장
	 * @호출방법 ssafywolrd/room/enter
	 * @param RoomHasUserDTO
	 * @return String
	 * @throws Exception 
	 */
	@RequestMapping(value="/room/enter", method=RequestMethod.POST, produces="application/json; charset=utf8")
	@ResponseBody
	public ResponseEntity<String> enterRoom(@RequestBody RoomHasUserDTO dto){
		try {
			System.out.println(dto.getRidx()+" "+dto.getUidx());
			rService.enterRoom(dto.getRidx(), dto.getUidx());
			System.out.println("ok");
			return ResponseEntity.ok().body("Room Enter!");
		} catch (Exception e) {
			return ResponseEntity.badRequest().body("Room Enter Fail!");
		}
	}
	
	/**
	 * 11-06 : 이규찬 
	 * @기능 방이름 변경
	 * @호출방법 ssafywolrd/room/update
	 * @param RoomDTO
	 * @return String
	 * @throws Exception 
	 */
	@RequestMapping(value="/room/update", method=RequestMethod.POST, produces="application/json; charset=utf8")
	@ResponseBody
	public ResponseEntity<String> updateRoom(@RequestBody RoomDTO dto){
			try {
				rService.updateRoom(dto);
				return ResponseEntity.ok().body("방 이름 변경 : "+dto.getRidx()+" "+dto.getRname());
			} catch (Exception e) {
				return ResponseEntity.badRequest().body("방이름 변경 실패");
			}
		}
	}