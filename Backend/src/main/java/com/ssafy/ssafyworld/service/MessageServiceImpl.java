package com.ssafy.ssafyworld.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.ssafy.ssafyworld.dao.MessageDAO;
import com.ssafy.ssafyworld.dto.MessageDTO;
import com.ssafy.ssafyworld.dto.imgDTO;

@Service
public class MessageServiceImpl implements MessageService {

	@Inject
	private MessageDAO dao;

	@Override
	public List<MessageDTO> selectMessages() throws Exception {
		return dao.selectMessages();
	}

	@Override
	public List<MessageDTO> roomMessages(int ridx) throws Exception {
		return dao.roomMessages(ridx);
	}

	@Override
	public int insertMessage(MessageDTO message) throws Exception {
		return dao.insertMessage(message);
	}

	@Override
	public void insertImg(imgDTO img) throws Exception {
		dao.insertImg(img);
	}

	@Override
	public List<imgDTO> roomImgs(int ridx) throws Exception {
		return dao.roomImgs(ridx);
	}

}
