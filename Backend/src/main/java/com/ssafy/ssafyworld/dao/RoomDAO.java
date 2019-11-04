package com.ssafy.ssafyworld.dao;

import java.util.List;

import com.ssafy.ssafyworld.dto.RoomDTO;
import com.ssafy.ssafyworld.dto.UserDTO;

public interface RoomDAO { 
	public List<UserDTO> selectRoom(int ridx) throws Exception;
	public int createRoom(String rname) throws Exception;
	public void deleteRoom(int ridx) throws Exception;
	public List<RoomDTO> selectRooms() throws Exception;
	public void enterRoom(int room, int user) throws Exception;
}
