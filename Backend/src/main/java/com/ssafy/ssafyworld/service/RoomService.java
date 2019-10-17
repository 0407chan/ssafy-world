package com.ssafy.ssafyworld.service;

import com.ssafy.ssafyworld.dto.RoomDTO;

public interface RoomService {
	public RoomDTO selectRoom(int rid) throws Exception;
}
