package com.ssafy.ssafyworld.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ssafy.ssafyworld.dto.RoomDTO;
import com.ssafy.ssafyworld.dto.UserDTO;

@Repository
public class RoomDAOImpl implements RoomDAO {


    @Autowired
    private SqlSession sqlSession;
    
    private static final String Namespace = "com.ssafy.ssafyworld.mapper.roomMapper";
    

	@Override
	public int createRoom(String rname) {
		sqlSession.insert(Namespace+".createRoom",rname);
		RoomDTO room=sqlSession.selectOne(Namespace+".selectRoomName",rname);
		return room.getRid();
	}

	@Override
	public void deleteRoom(int rid) throws Exception {
		sqlSession.delete(Namespace+".deleteRoom",rid);
	}

	@Override
	public List<RoomDTO> selectRooms() throws Exception {
		List<RoomDTO> rooms = sqlSession.selectList(Namespace+".selectRooms");
		return rooms;
	}

	@Override
	public List<UserDTO> selectRoom(int rid) throws Exception {
		List<UserDTO> list = sqlSession.selectList(Namespace+".selectRoom",rid);
		return list;
	}

}
