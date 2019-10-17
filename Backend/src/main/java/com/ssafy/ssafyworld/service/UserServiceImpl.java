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
    public List<UserDTO> selectUser() throws Exception {
        return dao.selectUser();
    }

}
