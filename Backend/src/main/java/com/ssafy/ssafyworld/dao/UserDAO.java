package com.ssafy.ssafyworld.dao;

import java.util.List;

import com.ssafy.ssafyworld.dto.UserDTO;

public interface UserDAO {
   public List<UserDTO> selectUsers() throws Exception;
   public void register(UserDTO user) throws Exception;
   public UserDTO login(UserDTO user) throws Exception;
   
   public UserDTO getUser(UserDTO user) throws Exception;
}
