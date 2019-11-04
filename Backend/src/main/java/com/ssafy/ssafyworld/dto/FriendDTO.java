package com.ssafy.ssafyworld.dto;

public class FriendDTO {
	private int fidx;
	private int uidx;
	private int friend;
	
	
	public FriendDTO(int fidx, int uidx, int friend) {
		super();
		this.fidx = fidx;
		this.uidx = uidx;
		this.friend = friend;
	}
	public int getFidx() {
		return fidx;
	}
	public void setFidx(int fidx) {
		this.fidx = fidx;
	}
	public int getUidx() {
		return uidx;
	}
	public void setUidx(int uidx) {
		this.uidx = uidx;
	}
	public int getFriend() {
		return friend;
	}
	public void setFriend(int friend) {
		this.friend = friend;
	}
	
	@Override
	public String toString() {
		return "FriendDTO [fidx=" + fidx + ", uidx=" + uidx + ", friend=" + friend + "]";
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + fidx;
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
		if (fidx != other.fidx)
			return false;
		return true;
	}
	
}
