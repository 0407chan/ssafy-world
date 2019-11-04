package com.ssafy.ssafyworld.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.ssafy.ssafyworld.dao.UserDAO;
import com.ssafy.ssafyworld.dto.RoomDTO;
import com.ssafy.ssafyworld.dto.UserDTO;
 
@Service
public class UserServiceImpl implements UserService {
 
    @Inject
    private UserDAO dao;
    
    @Override
    public List<UserDTO> selectUsers() throws Exception {
        return dao.selectUsers();
    }

	@Override
	public int register(UserDTO user) throws Exception {
		int n = dao.register(user);
		return n;
	}

	@Override
	public UserDTO login(UserDTO user) throws Exception {
		return dao.login(user);
	}

	@Override
	public UserDTO getUser(UserDTO user) throws Exception {
		System.out.println("UserServiceImpl getUser = "+user);
		return dao.getUser(user);
	}

	@Override
	public List<RoomDTO> selectUserRooms(int uidx) throws Exception {
		return dao.selectUserRooms(uidx);
	}

	@Override
	public UserDTO getUserInfo(int uidx) throws Exception {
		return dao.getUserInfo(uidx);
	}

	@Override
	public void update(UserDTO user) throws Exception {
		dao.update(user);
	}

	@Override
	public void deleteUser(int uidx) throws Exception {
		dao.deleteUser(uidx);
	}
}
