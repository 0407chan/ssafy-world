package com.ssafy.ssafyworld;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
public class RoomController {
	
	@Inject
	private RoomService rService;
	
	private static final Logger logger = LoggerFactory.getLogger(RoomController.class);

	@RequestMapping(value = "/room/{rid}", method = RequestMethod.GET)
	@ResponseBody
	public RoomDTO selectRoom(@PathVariable("rid") int rid) throws Exception {
		System.out.println(rid);
		System.out.println("방 선택 완료!");
		return rService.selectRoom(rid);
	}
	
}
