package com.example.truyenol.model;

public class user {
    public int id;
    public String username;
    public String password;
    public String fullName;
    public String email;
    public String linkAva;
    public String position;

    public user(String username, String password, String email, int position) {
    }

    public user(String username1, String s, String username, String password, String fullName, String email) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        this.email = email;
        this.linkAva = linkAva;
        this.position = position;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLinkAva() {
        return linkAva;
    }

    public void setLinkAva(String linkAva) {
        this.linkAva = linkAva;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
}
