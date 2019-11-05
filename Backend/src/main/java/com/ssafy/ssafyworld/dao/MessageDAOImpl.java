package com.ssafy.ssafyworld.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ssafy.ssafyworld.dto.MessageDTO;
import com.ssafy.ssafyworld.dto.imgDTO;

@Repository
public class MessageDAOImpl implements MessageDAO {

	@Autowired
	private SqlSession sqlSession;

	private static final String Namespace = "com.ssafy.ssafyworld.mapper.messageMapper";

	  
	@Override
	public List<MessageDTO> selectMessages() throws Exception {
    	List<MessageDTO> list=sqlSession.selectList(Namespace+".selectMessages");
    	return list;
	}


	@Override
	public List<MessageDTO> roomMessages(int ridx) throws Exception {
		List<MessageDTO> list=sqlSession.selectList(Namespace+".roomMessages",ridx);
    	return list;
	}


	@Override
	public int insertMessage(MessageDTO message) throws Exception {
		return sqlSession.insert(Namespace+".insertMessage",message);
				
	}


	@Override
	public void insertImg(imgDTO img) throws Exception {
		sqlSession.insert(Namespace+".insertImg",img);
	}


	@Override
	public List<imgDTO> roomImgs(int ridx) throws Exception {
		List<imgDTO> list = sqlSession.selectList(Namespace+".roomImgs",ridx); 
		return list;
	}
	
	

}
