package com.ssafy.ssafyworld.dto;

import java.io.Serializable;

public class UserDTO implements Serializable{
	private int uidx;
	private String uid;
	private String uname;
	private String password;
	private String img;
	private int staff;
	
	
	public int getUidx() {
		return uidx;
	}
	public void setUidx(int uidx) {
		this.uidx = uidx;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public int getStaff() {
		return staff;
	}
	public void setStaff(int staff) {
		this.staff = staff;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public UserDTO() {
		super();
	}
	
	public UserDTO(int uidx, String uid, String uname, String password, String img, int staff) {
		super();
		this.uidx = uidx;
		this.uid = uid;
		this.uname = uname;
		this.password = password;
		this.img = img;
		this.staff = staff;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((uid == null) ? 0 : uid.hashCode());
		return result;
	}
	
//  '{ "name":"John", "age":30, "city":"New York"}'
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("{\"uid\":\"");
		builder.append(uid);
		builder.append("\", \"uidx\":\"");
		builder.append(uidx);
		builder.append("\", \"uname\":\"");
		builder.append(uname);
		builder.append("\", \"img\":\"");
		builder.append(img);
		builder.append("\", \"staff\":\"");
		builder.append(staff);
		builder.append("\"}");
		return builder.toString();
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserDTO other = (UserDTO) obj;
		if (uid == null) {
			if (other.uid != null)
				return false;
		} else if (!uid.equals(other.uid))
			return false;
		return true;
	}
	
	
}
