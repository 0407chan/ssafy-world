package com.ssafy.ssafyworld.dao;

import java.util.List;

import com.ssafy.ssafyworld.dto.FriendDTO;


public interface FriendDAO {
	public List<FriendDTO> selectFriends() throws Exception;
	public FriendDTO getFriend(FriendDTO friend) throws Exception;
	public int addFriend(FriendDTO friend) throws Exception;
	public void deleteFriend(FriendDTO friend) throws Exception;
}
