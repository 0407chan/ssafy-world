package com.ssafy.ssafyworld.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.ssafy.ssafyworld.dao.MessageDAO;
import com.ssafy.ssafyworld.dto.MessageDTO;

@Service
public class MessageServiceImpl implements MessageService {

	@Inject
	private MessageDAO dao;

	@Override
	public List<MessageDTO> selectMessages() throws Exception {
		return dao.selectMessages();
	}

	@Override
	public List<MessageDTO> roomMessages(int rid) throws Exception {
		return dao.roomMessages(rid);
	}

	@Override
	public int insertMessage(MessageDTO message) throws Exception {
		return dao.insertMessage(message);
	}

}
