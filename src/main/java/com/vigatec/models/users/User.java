// src/main/java/com/starken/automation/framework/pojo/User.java
package com.vigatec.models.users;

public class User {
    private String username;
    private String password;

    // Constructores
    public User() {}

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    // Getters y Setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{username='" + username + "', password='" + password + "'}";
    }
}

