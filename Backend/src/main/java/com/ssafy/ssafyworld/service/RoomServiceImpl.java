package com.ssafy.ssafyworld.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.ssafy.ssafyworld.dao.RoomDAO;
import com.ssafy.ssafyworld.dto.RoomDTO;
import com.ssafy.ssafyworld.dto.UserDTO;

@Service
public class RoomServiceImpl implements RoomService {

    @Inject
    private RoomDAO dao;
    
	
	@Override
	public List<UserDTO> selectRoom(int rid) throws Exception {
		return dao.selectRoom(rid);
	}


	@Override
	public int createRoom(String rname) throws Exception {
		return dao.createRoom(rname);
	}


	@Override
	public void deleteRoom(int rid) throws Exception {
		dao.deleteRoom(rid);
	}


	@Override
	public List<RoomDTO> selectRooms() throws Exception {
		return dao.selectRooms();
	}

}
