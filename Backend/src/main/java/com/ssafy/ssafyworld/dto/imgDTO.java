package com.ssafy.ssafyworld.dto;

public class imgDTO {
	int iidx;
	int ridx;
	String img;
	public int getIidx() {
		return iidx;
	}
	public void setIidx(int iidx) {
		this.iidx = iidx;
	}
	public int getRidx() {
		return ridx;
	}
	public void setRidx(int ridx) {
		this.ridx = ridx;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + iidx;
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
		imgDTO other = (imgDTO) obj;
		if (iidx != other.iidx)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "imgDTO [iidx=" + iidx + ", ridx=" + ridx + ", img=" + img + "]";
	}
	
}
