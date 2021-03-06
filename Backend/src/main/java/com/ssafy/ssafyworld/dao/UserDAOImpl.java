package com.ssafy.ssafyworld.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ssafy.ssafyworld.dto.RoomDTO;
import com.ssafy.ssafyworld.dto.UserDTO;
 
@Repository
public class UserDAOImpl implements UserDAO {
 
    @Autowired
    private SqlSession sqlSession;
    
    private static final String Namespace = "com.ssafy.ssafyworld.mapper.userMapper";
    
    @Override
    public List<UserDTO> selectUsers() throws Exception {
    	List<UserDTO> list=sqlSession.selectList(Namespace+".selectUsers");
    	return list;
    }

	@Override
	public int register(UserDTO user) throws Exception {
		int n =sqlSession.insert(Namespace+".register", user);
		return n;
	}

	@Override
	public UserDTO login(UserDTO user) throws Exception {
		UserDTO dto=sqlSession.selectOne(Namespace+".login",user);
		return dto;
	}

	
	/** 2019.10.24 이찬호
	 * 기능 : 해당 유저가 있는지 찾아온다
	 * 입력 : user
	 * 리턴 : 유저가 존재하면 해당 user, 없으면 null
	 * */
	@Override
	public UserDTO getUser(UserDTO user) throws Exception {
		System.out.println("getUser user = "+user);
		UserDTO dto = sqlSession.selectOne(Namespace+".getUser",user.getUidx());
		System.out.println("getUser dto = "+dto);
		return dto ;
	}

	@Override
	public List<RoomDTO> selectUserRooms(int uidx) throws Exception {
		List<RoomDTO> list = sqlSession.selectList(Namespace+".selectUserRooms", uidx);
		return list;
	}

	@Override
	public UserDTO getUserInfo(int uidx) throws Exception {
		System.out.println("getUserInfo udix="+uidx);
		UserDTO dto = sqlSession.selectOne(Namespace+".getUserInfo", uidx);
		System.out.println("getUserInfo dto="+dto);
		return dto;
	}

	@Override
	public void update(UserDTO user) throws Exception {
		sqlSession.update(Namespace+".update", user);
	}

	@Override
	public void deleteUser(int uidx) throws Exception {
		sqlSession.delete(Namespace+".deleteUser",uidx);
	}

	@Override
	public UserDTO getUserLogin(String uid) throws Exception {
		System.out.println("getUserLogin user = "+uid);
		UserDTO dto = sqlSession.selectOne(Namespace+".getUserLogin",uid);
		System.out.println("getUserLogin dto = "+dto);
		return dto ;
	}

	@Override
	public void adminUpdate(UserDTO user) throws Exception {
		sqlSession.update(Namespace+".adminUpdate",user);
	}

	@Override
	public List<UserDTO> searchUserAll(String uid) {
		List<UserDTO> list = sqlSession.selectList(Namespace+".searchUserAll", uid);
		return list;
	}
 
}
