package com.ssafy.ssafyworld.service;

import java.util.List;
import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.ssafy.ssafyworld.dao.UserDAO;
import com.ssafy.ssafyworld.dto.UserDTO;
import com.ssafy.ssafyworld.util.SHA256Util;
 
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
		String salt = SHA256Util.generateSalt();
		user.setSalt(salt);
		String password = user.getPassword();
		password = SHA256Util.getEncrypt(password, salt);
		
		user.setPassword(password);
		System.out.println("회원가입 ServiceImpl");
		return dao.register(user);
	}

	@Override
	public UserDTO login(UserDTO user) throws Exception {
		System.out.println("로그인 ServiceImpl");
		
		String salt = dao.getSaltById(user.getUid());
		String password = user.getPassword();
		
		password = SHA256Util.getEncrypt(password, salt);
		user.setPassword(password);
		
		return dao.login(user);
	}

	@Override
	public UserDTO getUser(UserDTO user) throws Exception {
		System.out.println("유저찾기 ServiceImpl");
		return dao.getUser(user);
	}

}
