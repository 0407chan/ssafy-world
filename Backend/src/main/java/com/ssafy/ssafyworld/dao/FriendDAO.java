package com.ssafy.ssafyworld.dao;

import java.util.List;

import com.ssafy.ssafyworld.dto.FriendDTO;


public interface FriendDAO {
	public List<FriendDTO> selectFriends() throws Exception; // 전체 친구 출력
	public List<String> selectFriend(int uidx) throws Exception; // UID 해당 친구 출력
	public FriendDTO getFriend(FriendDTO friend) throws Exception;
	public int addFriend(FriendDTO friend) throws Exception;
	public void deleteFriend(FriendDTO friend) throws Exception;
}
