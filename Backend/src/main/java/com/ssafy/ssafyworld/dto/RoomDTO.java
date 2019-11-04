package com.ssafy.ssafyworld.dto;

public class RoomDTO {
	private int ridx;
	private String rname;
	
	public int getRidx() {
		return ridx;
	}
	public void setRidx(int ridx) {
		this.ridx = ridx;
	}
	public String getRname() {
		return rname;
	}
	public void setRname(String rname) {
		this.rname = rname;
	}
	
	@Override
	public String toString() {
		return "RoomDTO [ridx=" + ridx + ", rname=" + rname + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ridx;
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
		if (ridx != other.ridx)
			return false;
		return true;
	}
	
	
	
}
