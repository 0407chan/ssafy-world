package com.ssafy.ssafyworld.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.ssafy.ssafyworld.dao.FriendDAO;
import com.ssafy.ssafyworld.dto.FriendDTO;

@Service
public class FriendServiceImpl implements FriendService {

	@Inject
	private FriendDAO dao;
	
	@Override
	public List<FriendDTO> selectFriends() throws Exception {
		return dao.selectFriends();
	}

	@Override
	public List<String> selectFriend(String uid) throws Exception {
		return dao.selectFriend(uid);
	}
	@Override
	public FriendDTO getFriend(FriendDTO friend) throws Exception {
		return dao.getFriend(friend);
	}

	@Override
	public int addFriend(FriendDTO friend) throws Exception {
		return dao.addFriend(friend);
	}

	@Override
	public void deleteFriend(FriendDTO friend) throws Exception {
		dao.deleteFriend(friend);
	}

	

}
