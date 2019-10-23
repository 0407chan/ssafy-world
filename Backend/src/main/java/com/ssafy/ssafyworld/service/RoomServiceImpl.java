package com.ssafy.ssafyworld.service;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.ssafy.ssafyworld.dao.RoomDAO;
import com.ssafy.ssafyworld.dao.UserDAO;
import com.ssafy.ssafyworld.dto.RoomDTO;

@Service
public class RoomServiceImpl implements RoomService {

    @Inject
    private RoomDAO dao;
    
	
	@Override
	public RoomDTO selectRoom(int rid) throws Exception {
		return dao.selectRoom(rid);
	}


	@Override
	public int createRoom(String rname) {
		return dao.createRoom(rname);
		
	}

}
