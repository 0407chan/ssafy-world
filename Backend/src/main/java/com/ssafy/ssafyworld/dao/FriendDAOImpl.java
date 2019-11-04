package com.ssafy.ssafyworld.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ssafy.ssafyworld.dto.FriendDTO;
import com.ssafy.ssafyworld.dto.MessageDTO;
import com.ssafy.ssafyworld.dto.UserDTO;

@Repository
public class FriendDAOImpl implements FriendDAO {

	@Autowired
	private SqlSession sqlSession;

	private static final String Namespace = "com.ssafy.ssafyworld.mapper.friendMapper";
	  
	@Override
	public List<FriendDTO> selectFriends() throws Exception {
		List<FriendDTO> list=sqlSession.selectList(Namespace+".selectFriends");
    	return list;
	}
	
	  
	@Override
	public List<String> selectFriend(int uidx) throws Exception {
		List<String> list=sqlSession.selectList(Namespace+".selectFriend",uidx);
    	return list;
	}

	@Override
	public int addFriend(FriendDTO friend) throws Exception {
		return sqlSession.insert(Namespace+".addFriend",friend);
	}

	@Override
	public void deleteFriend(FriendDTO friend) throws Exception {
		sqlSession.delete(Namespace+".deleteFriend",friend);
	}

	@Override
	public FriendDTO getFriend(FriendDTO friend) throws Exception {
		return sqlSession.selectOne(Namespace+".getFriend",friend);
	}

}
