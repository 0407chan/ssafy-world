package com.ssafy.ssafyworld.dto;

public class FriendDTO {
	private int fid;
	private String uid;
	private String friend;
	public int getFid() {
		return fid;
	}
	public void setFid(int fid) {
		this.fid = fid;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getFriend() {
		return friend;
	}
	public void setFriend(String friend) {
		this.friend = friend;
	}
	@Override
	public String toString() {
		return "FriendDTO [fid=" + fid + ", uid=" + uid + ", friend=" + friend + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + fid;
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
		FriendDTO other = (FriendDTO) obj;
		if (fid != other.fid)
			return false;
		return true;
	}
	
	
}
