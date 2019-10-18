package com.ssafy.ssafyworld.dto;

import java.io.Serializable;

public class MessageDTO implements Serializable{
	private int mid;
	private String text;
	private String sendtime;
	private String uid;
	private int rid;
	
	
	public MessageDTO(int mid, String text, String sendtime, String uid, int rid) {
		this.mid = mid;
		this.text = text;
		this.sendtime = sendtime;
		this.uid = uid;
		this.rid = rid;
	}
	public int getMid() {
		return mid;
	}
	public void setMid(int mid) {
		this.mid = mid;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getSendtime() {
		return sendtime;
	}
	public void setSendtime(String sendtime) {
		this.sendtime = sendtime;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public int getRid() {
		return rid;
	}
	public void setRid(int rid) {
		this.rid = rid;
	}
	@Override
	public String toString() {
		return "MessageDTO [mid=" + mid + ", text=" + text + ", sendtime=" + sendtime + ", uid=" + uid + ", rid=" + rid
				+ "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + mid;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MessageDTO other = (MessageDTO) obj;
		if (mid != other.mid)
			return false;
		return true;
	}
	
	

}
