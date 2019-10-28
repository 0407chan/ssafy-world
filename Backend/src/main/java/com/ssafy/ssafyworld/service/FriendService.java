package com.ssafy.ssafyworld.service;

import java.util.List;

import com.ssafy.ssafyworld.dto.FriendDTO;

public interface FriendService {
	public List<FriendDTO> selectFriends() throws Exception;
	public FriendDTO getFriend(FriendDTO friend) throws Exception;
	public int addFriend(FriendDTO friend) throws Exception;
	public void deleteFriend(FriendDTO friend) throws Exception;
}
