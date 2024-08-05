package com.jvsc.Request.user;

import java.sql.Date;

public class AddUserDto {
    private String name;
    private String email;
    private String password;
    private String birthDate;

	public AddUserDto(String name, String email, String password, String birthDate) {
		this.name = name;
		this.email = email;
		this.password = password;
		this.birthDate = birthDate;
	}

	public AddUserDto() { }

	

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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}
}
