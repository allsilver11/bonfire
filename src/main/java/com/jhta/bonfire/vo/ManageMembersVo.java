package com.jhta.bonfire.vo;

import java.sql.Date;

public class ManageMembersVo {
	private String id;
	private String pwd;
	private String name;
	private String email;
	private Date birth;
	private String gender;
	private String phone;
	private String roadfulladdr;
	private int enabled;
	private Date regdate;
	private String addrdetail;
	private String zipno;
	private String authority;
	public ManageMembersVo() {
		super();
	}
	public ManageMembersVo(String id, String pwd, String name, String email, Date birth, String gender, String phone,
			String roadfulladdr, int enabled, Date regdate, String addrdetail, String zipno, String authority) {
		super();
		this.id = id;
		this.pwd = pwd;
		this.name = name;
		this.email = email;
		this.birth = birth;
		this.gender = gender;
		this.phone = phone;
		this.roadfulladdr = roadfulladdr;
		this.enabled = enabled;
		this.regdate = regdate;
		this.addrdetail = addrdetail;
		this.zipno = zipno;
		this.authority = authority;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getBirth() {
		return birth;
	}
	public void setBirth(Date birth) {
		this.birth = birth;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getRoadfulladdr() {
		return roadfulladdr;
	}
	public void setRoadfulladdr(String roadfulladdr) {
		this.roadfulladdr = roadfulladdr;
	}
	public int getEnabled() {
		return enabled;
	}
	public void setEnabled(int enabled) {
		this.enabled = enabled;
	}
	public Date getRegdate() {
		return regdate;
	}
	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
	public String getAddrdetail() {
		return addrdetail;
	}
	public void setAddrdetail(String addrdetail) {
		this.addrdetail = addrdetail;
	}
	public String getZipno() {
		return zipno;
	}
	public void setZipno(String zipno) {
		this.zipno = zipno;
	}
	public String getAuthority() {
		return authority;
	}
	public void setAuthority(String authority) {
		this.authority = authority;
	}
	
}
