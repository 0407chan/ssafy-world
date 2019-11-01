package com.ssafy.ssafyworld.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        List<RoomDTO> list=sqlSession.selectList(Namespace+".selectRoomName",rname);
        return list.get(list.size()-1).getRid();
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

	@Override
	public void enterRoom(int room, String user) throws Exception {
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("rid", room);
		parameters.put("uid", user);
		sqlSession.insert(Namespace+".enterRoom",parameters);
	}

}
