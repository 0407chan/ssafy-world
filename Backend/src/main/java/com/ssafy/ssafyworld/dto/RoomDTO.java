package com.ssafy.ssafyworld.dto;

public class RoomDTO {
	private int rid;
	private String rname;
	
	public int getRid() {
		return rid;
	}
	public void setRid(int rid) {
		this.rid = rid;
	}
	public String getRname() {
		return rname;
	}
	public void setRname(String rname) {
		this.rname = rname;
	}
	
	@Override
	public String toString() {
		return "RoomDTO [rid=" + rid + ", rname=" + rname + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + rid;
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
		RoomDTO other = (RoomDTO) obj;
		if (rid != other.rid)
			return false;
		return true;
	}
	
	
	
}
