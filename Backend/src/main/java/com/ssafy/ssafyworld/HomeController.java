package com.ssafy.ssafyworld;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.ssafyworld.dto.UserVO;
import com.ssafy.ssafyworld.service.UserService;

/**
 * Handles requests for the application home page.
 */
@RestController
public class HomeController {
	
	@Inject
    private UserService service;
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	
	/**
	 * 
	 * 10-15 : 최재형 
	 * @기능 유저 전체 리스트를 가져옴
	 * @호출방법 ssafywolrd/user
	 * @param X
	 * @return List<UserVO> user 데이터
	 */
	@RequestMapping(value = "/user", method = RequestMethod.GET)
	@ResponseBody
	public List<UserVO> home() throws Exception {
		logger.info("Welcome home! The client locale is {}.");
		System.out.println("유저 데이터 호출 완료");
		return service.selectUser();
	}
	
}
