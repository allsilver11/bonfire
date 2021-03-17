package com.jhta.bonfire.vo;

public class AuthVo {
	private String id;
	private String authority;
	public AuthVo() {}
	public AuthVo(String id, String authority) {
		super();
		this.id = id;
		this.authority = authority;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}
}
