package com.ssafy.ssafyworld.dao;

import java.util.*;
import com.ssafy.ssafyworld.dto.MessageDTO;

public interface MessageDAO {
	
	
	public MessageDTO select() throws Exception;
	public void send() throws Exception;
	public MessageDTO delete() throws Exception;
	
	
}
