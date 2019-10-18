package com.ssafy.ssafyworld.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ssafy.ssafyworld.dto.UserDTO;
 
@Repository
public class UserDAOImpl implements UserDAO {
 
    @Inject
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
	public void register(UserDTO user) throws Exception {
		System.out.println("회원가입 DAOImpl");
		int n =sqlSession.insert(Namespace+".register", user);
		System.out.println(n);
	}

	@Override
	public UserDTO login(UserDTO user) throws Exception {
		System.out.println("로그인 DAOImpl");
		UserDTO dto=sqlSession.selectOne(Namespace+".login",user);
		System.out.println("로그인성공:"+user);
		return dto;
	}
 
}
