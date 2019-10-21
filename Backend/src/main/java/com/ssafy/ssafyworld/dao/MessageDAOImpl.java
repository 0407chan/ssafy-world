package com.ssafy.ssafyworld.dao;

import java.util.List;

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
	public List<MessageDTO> selectMessages() throws Exception {

    	List<MessageDTO> list=sqlSession.selectList(Namespace+".selectMessages");
    	System.out.println(list);
    	return list;
	}


	@Override
	public List<MessageDTO> roomMessages(int rid) throws Exception {
		List<MessageDTO> list=sqlSession.selectList(Namespace+".roomMessages",rid);
    	System.out.println(list);
    	return list;
	}

}
