package com.ssafy.ssafyworld.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ssafy.ssafyworld.dto.UserDTO;
 
@Repository
public class UserDAOImpl implements UserDAO {
 
    @Autowired
    private SqlSession sqlSession;
    
    private static final String Namespace = "com.ssafy.ssafyworld.mapper.userMapper";
    
    @Override
    public List<UserDTO> selectUsers() throws Exception {
    	System.out.println(sqlSession);
    	List<UserDTO> list=sqlSession.selectList(Namespace+".selectUsers");
    	System.out.println(list);
    	return list;
    }

	@Override
	public int register(UserDTO user) throws Exception {
		System.out.println("회원가입 DAOImpl");
		int n =sqlSession.insert(Namespace+".register", user);
		return n;
	}

	@Override
	public UserDTO login(UserDTO user) throws Exception {
		System.out.println("로그인 DAOImpl");
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
		UserDTO dto = sqlSession.selectOne(Namespace+".login",user.getUid());
		System.out.println("getUser = " + dto);
		return dto ;
	}
 
}
