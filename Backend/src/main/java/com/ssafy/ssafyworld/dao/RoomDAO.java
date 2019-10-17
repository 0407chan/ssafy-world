package com.ssafy.ssafyworld.dao;

import com.ssafy.ssafyworld.dto.RoomDTO;

public interface RoomDAO { 
	public RoomDTO selectRoom(int rid) throws Exception;
}
