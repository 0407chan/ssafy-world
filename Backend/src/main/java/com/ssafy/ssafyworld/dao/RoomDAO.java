package com.ssafy.ssafyworld.dao;

import java.util.List;

import com.ssafy.ssafyworld.dto.RoomDTO;

public interface RoomDAO { 
	public RoomDTO selectRoom(int rid) throws Exception;
	public int createRoom(String rname) throws Exception;
	public void deleteRoom(int rid) throws Exception;
	public List<RoomDTO> selectRooms() throws Exception;
}
