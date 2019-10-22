package com.ssafy.ssafyworld.dto;

import java.io.Serializable;

public class params implements Serializable{
	private String uid;
	private String uname;
	private String password;
	
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
	
	public params() {
		super();
	}
	
	public params(String uid, String uname, String password) {
		super();
		this.setUid(uid);
		this.setUname(uname);
		this.setPassword(password);
	}
	@Override
	public String toString() {
		return "UserDTO [uid=" + uid + ", uname=" + uname + ", password=" + password + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((uid == null) ? 0 : uid.hashCode());
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
		params other = (params) obj;
		if (uid == null) {
			if (other.uid != null)
				return false;
		} else if (!uid.equals(other.uid))
			return false;
		return true;
	}
	
	
}