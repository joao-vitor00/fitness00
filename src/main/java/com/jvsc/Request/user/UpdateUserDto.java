package com.jvsc.Request.user;

import java.util.Date;

public class UpdateUserDto {
    private String name;
    private String email;
    private String newPassword;
    private String birthDate;
    private long   id; 
    

    public UpdateUserDto(String name, String email, String newPassword, String birthDate, long id) {
        this.name = name;
        this.email = email;
        this.newPassword = newPassword;
        this.birthDate = birthDate;
        this.id = id;
        
    }

    public UpdateUserDto() {
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
    public String getNewPassword() {
        return newPassword;
    }
    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
    public String getBirthDate() {
        return birthDate;
    }
    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    
}
