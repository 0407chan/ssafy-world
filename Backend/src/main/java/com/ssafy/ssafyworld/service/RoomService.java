package com.ssafy.ssafyworld.service;

import java.util.List;

import com.ssafy.ssafyworld.dto.RoomDTO;
import com.ssafy.ssafyworld.dto.UserDTO;

public interface RoomService {
	public List<UserDTO> selectRoom(int ridx) throws Exception;
	public RoomDTO getRoom(int ridx) throws Exception;
	public int createRoom(String rname) throws Exception;
	public void deleteRoom(int ridx) throws Exception;
	public List<RoomDTO> selectRooms() throws Exception;
	public void enterRoom(int room, int user) throws Exception;
	public void updateRoom(RoomDTO dto) throws Exception;
}
