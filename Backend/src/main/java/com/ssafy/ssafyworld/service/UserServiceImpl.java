package com.ssafy.ssafyworld.service;

import java.util.List;
import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.ssafy.ssafyworld.dao.UserDAO;
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
		System.out.println("회원가입 ServiceImpl");
		int n = dao.register(user);
		return n;
	}

	@Override
	public UserDTO login(UserDTO user) throws Exception {
		System.out.println("로그인 ServiceImpl");
		return dao.login(user);
	}
}
