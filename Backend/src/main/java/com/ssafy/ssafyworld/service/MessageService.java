package com.ssafy.ssafyworld.service;

import java.util.List;

import com.ssafy.ssafyworld.dto.MessageDTO;

public interface MessageService {
	public List<MessageDTO> selectMessages() throws Exception;
	public List<MessageDTO> roomMessages(int rid) throws Exception;	
	public int insertMessage(MessageDTO message) throws Exception;
}
