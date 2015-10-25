package com.springtest.model;

public class User {
	
	private int id;
	private String name;
	private String emailaddress;
	
	
	public User(int id, String name, String emailaddress) {
		super();
		this.id = id;
		this.name = name;
		this.emailaddress = emailaddress;
	}
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmailaddress() {
		return emailaddress;
	}

	public void setEmailaddress(String emailaddress) {
		this.emailaddress = emailaddress;
	}
}
