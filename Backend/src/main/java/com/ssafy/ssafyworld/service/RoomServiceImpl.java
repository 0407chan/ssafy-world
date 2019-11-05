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
	public List<UserDTO> selectRoom(int ridx) throws Exception {
		return dao.selectRoom(ridx);
	}


	@Override
	public int createRoom(String rname) throws Exception {
		return dao.createRoom(rname);
	}


	@Override
	public void deleteRoom(int ridx) throws Exception {
		dao.deleteRoom(ridx);
	}


	@Override
	public List<RoomDTO> selectRooms() throws Exception {
		return dao.selectRooms();
	}


	@Override
	public void enterRoom(int room, int user) throws Exception {
		dao.enterRoom(room,user);
	}
	

	@Override
	public RoomDTO getRoom(int ridx) throws Exception {
		return dao.getRoom(ridx);
	}

}
