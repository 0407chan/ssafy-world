package com.ssafy.ssafyworld.service;

import java.util.List;

import com.ssafy.ssafyworld.dto.MessageDTO;
import com.ssafy.ssafyworld.dto.imgDTO;

public interface MessageService {
	public List<MessageDTO> selectMessages() throws Exception;
	public List<MessageDTO> roomMessages(int ridx) throws Exception;	
	public int insertMessage(MessageDTO message) throws Exception;
	public void insertImg(imgDTO img) throws Exception;
	public List<imgDTO> roomImgs(int ridx) throws Exception;
	
}
