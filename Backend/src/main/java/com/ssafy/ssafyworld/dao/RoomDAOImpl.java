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
		sqlSession.insert(Namespace + ".createRoom", rname);
		List<RoomDTO> list = sqlSession.selectList(Namespace + ".selectRoomName", rname);
		return list.get(list.size() - 1).getRidx();
	}

	@Override
	public void deleteRoom(int ridx) throws Exception {
		sqlSession.delete(Namespace + ".deleteRoom", ridx);
	}

	@Override
	public List<RoomDTO> selectRooms() throws Exception {
		List<RoomDTO> rooms = sqlSession.selectList(Namespace + ".selectRooms");
		return rooms;
	}

	@Override
	public List<UserDTO> selectRoom(int ridx) throws Exception {
		List<UserDTO> list = sqlSession.selectList(Namespace + ".selectRoom", ridx);
		return list;
	}

	@Override
	public void enterRoom(int room, int user) throws Exception {
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("ridx", room);
		parameters.put("uidx", user);
		sqlSession.insert(Namespace + ".enterRoom", parameters);
	}

	@Override
	public RoomDTO getRoom(int ridx) throws Exception {
		RoomDTO room = sqlSession.selectOne(Namespace + ".getRoom", ridx);
		return room;
	}

}
