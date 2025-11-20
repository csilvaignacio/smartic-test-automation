package com.vigatec.models.users;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class UserData {
    @JsonProperty("validUsers")
    private List<User> validUsers;
    private List<User> invalidUsers;

		//GENERAMOS GETTER Y SETTER 
		
  public List<User> getValidUsers() {
        return validUsers;
    }

    public void setValidUsers(List<User> validUsers) {
        this.validUsers = validUsers;
    }   

    public List<User> getInvalidUsers() {
        return invalidUsers;
    }

    public void setInvalidUsers(List<User> invalidUsers) {
        this.invalidUsers = invalidUsers;
    }   
}
