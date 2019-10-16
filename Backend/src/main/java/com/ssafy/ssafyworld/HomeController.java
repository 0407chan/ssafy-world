package com.ssafy.ssafyworld;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.ssafyworld.dto.UserVO;
import com.ssafy.ssafyworld.service.UserService;

/**
 * Handles requests for the application home page.
 */
@CrossOrigin(origins="*")
@RestController
public class HomeController {
	
	@Inject
    private UserService service;
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	
	/**
	 * 
	 * 10-15 : 理쒖옱�삎 
	 * @湲곕뒫 �쑀�� �쟾泥� 由ъ뒪�듃瑜� 媛��졇�샂
	 * @�샇異쒕갑踰� ssafywolrd/user
	 * @param X
	 * @return List<UserVO> user �뜲�씠�꽣
	 */
	@RequestMapping(value = "/user", method = RequestMethod.GET)
	@ResponseBody
	public List<UserVO> home() throws Exception {
		logger.info("Welcome home! The client locale is {}.");
		System.out.println("잘왔습니다");
		return service.selectUser();
	}
	
}
