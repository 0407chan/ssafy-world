package com.ssafy.ssafyworld.service;

import java.util.List;

import com.ssafy.ssafyworld.dto.UserDTO;

public interface UserService {

	public List<UserDTO> selectUser() throws Exception;
}
