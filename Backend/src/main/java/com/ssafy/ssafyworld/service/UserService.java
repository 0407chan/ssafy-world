package com.ssafy.ssafyworld.service;

import java.util.List;

import com.ssafy.ssafyworld.dto.RoomDTO;
import com.ssafy.ssafyworld.dto.UserDTO;

public interface UserService {

	public List<UserDTO> selectUsers() throws Exception;
	public int register(UserDTO user) throws Exception;
	public UserDTO login(UserDTO user) throws Exception;
	public UserDTO getUser(UserDTO user) throws Exception;
	public UserDTO getUserInfo(int uidx) throws Exception;
	public List<RoomDTO> selectUserRooms(int uidx) throws Exception;
	public void update(UserDTO user) throws Exception;
	public void deleteUser(int uidx) throws Exception;
}
