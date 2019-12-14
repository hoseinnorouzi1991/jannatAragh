package com.example.jannataragh.view.user;

public class User {

    private int id;
    private String full_name;
    private String phone_number;
    private String password;
    private String address;


    public User(int id, String full_name, String phone_number, String password, String address) {
        this.id = id;
        this.full_name = full_name;
        this.phone_number = phone_number;
        this.password = password;
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public String getFull_name() {
        return full_name;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public String getPassword() {
        return password;
    }

    public String getAddress() {
        return address;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
