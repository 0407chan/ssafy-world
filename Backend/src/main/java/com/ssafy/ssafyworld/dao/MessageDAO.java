package com.ssafy.ssafyworld.dao;

import java.util.List;

import com.ssafy.ssafyworld.dto.MessageDTO;

public interface MessageDAO {
	public List<MessageDTO> selectMessages() throws Exception;
}
