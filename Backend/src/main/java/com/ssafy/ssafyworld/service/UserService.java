package com.ssafy.ssafyworld.service;

import java.util.List;

import com.ssafy.ssafyworld.dto.UserVO;

public interface UserService {

	public List<UserVO> selectUser() throws Exception;
}
