package com.ssafy.ssafyworld.service;

import java.util.List;

import com.ssafy.ssafyworld.dto.RoomDTO;
import com.ssafy.ssafyworld.dto.UserDTO;

public interface RoomService {
	public List<UserDTO> selectRoom(int rid) throws Exception;
	public int createRoom(String rname) throws Exception;
	public void deleteRoom(int rid) throws Exception;
	public List<RoomDTO> selectRooms() throws Exception;
	public void enterRoom(int room, String user) throws Exception;
}
