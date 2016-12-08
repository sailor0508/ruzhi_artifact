package com.ruzhi.demo.groovy.model;

public class Member {
	private String name;
	private String email;

	public Member(String name, String email) {
		super();
		this.name = name;
		this.email = email;
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

	@Override
	public String toString() {
		return "Member [name=" + name + ", email=" + email + "]";
	}

}
