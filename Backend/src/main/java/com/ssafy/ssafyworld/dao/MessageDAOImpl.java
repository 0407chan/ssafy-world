package com.ssafy.ssafyworld.dao;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ssafy.ssafyworld.dto.MessageDTO;

@Repository
public class MessageDAOImpl implements MessageDAO {
	
	 @Inject
	 @Autowired
	 private SqlSession sqlSession;
	 private static final String Namespace = "com.ssafy.ssafyworld.mapper.messageMapper";
	

	@Override
	public MessageDTO select() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public void send() throws Exception {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public MessageDTO delete() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
