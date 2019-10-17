package com.ssafy.ssafyworld.dao;

import java.util.List;

import com.ssafy.ssafyworld.dto.UserDTO;

public interface UserDAO {
   public List<UserDTO> selectUser() throws Exception;
}
