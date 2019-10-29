package com.ssafy.ssafyworld.dao;

import java.util.List;

import com.ssafy.ssafyworld.dto.RoomDTO;
import com.ssafy.ssafyworld.dto.UserDTO;

public interface UserDAO {
   public List<UserDTO> selectUsers() throws Exception;
   public int register(UserDTO user) throws Exception;
   public UserDTO login(UserDTO user) throws Exception;
   public UserDTO getUser(UserDTO user) throws Exception;
   public List<RoomDTO> selectUserRooms(String uid) throws Exception;
}
