package com.ssafy.ssafyworld;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.ssafyworld.dto.RoomDTO;
import com.ssafy.ssafyworld.service.RoomService;

/**
 * Handles requests for the application home page.
 */
@CrossOrigin(origins="*")
@RestController
public class RoomController {
	
	@Inject
	private RoomService rService;
	
	private static final Logger logger = LoggerFactory.getLogger(RoomController.class);

	/**
	 * 10-18 : 박규빈 
	 * @기능 로그인
	 * @호출방법 ssafywolrd/room/{rid}
	 * @param rid
	 * @return RoomDTO
	 */

	@RequestMapping(value = "/room/{rid}", method = RequestMethod.GET)
	@ResponseBody
	public RoomDTO selectRoom(@PathVariable("rid") int rid) throws Exception {
		System.out.println(rid);
		System.out.println("방 선택 완료!");
		return rService.selectRoom(rid);
	}
	
	
}
