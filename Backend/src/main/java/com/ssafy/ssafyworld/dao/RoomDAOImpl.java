package com.ssafy.ssafyworld.dao;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ssafy.ssafyworld.dto.RoomDTO;

@Repository
public class RoomDAOImpl implements RoomDAO {


    @Autowired
    private SqlSession sqlSession;
    
    private static final String Namespace = "com.ssafy.ssafyworld.mapper.roomMapper";
    
	@Override
	public RoomDTO selectRoom(int rid) throws Exception {
    	RoomDTO room=sqlSession.selectOne(Namespace+".selectRoom",rid);
    	System.out.println(room);
		return room;
	}

}
