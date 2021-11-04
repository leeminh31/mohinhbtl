package com.example.mohinhbtl;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "user")

public class User implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String username;
    private String address;
    private String cccd;
    private String phone;
    private String date;

    public User( String username, String address, String cccd, String phone, String date) {
        this.username = username;
        this.address = address;
        this.cccd = cccd;
        this.phone = phone;
        this.date = date;
    }

    public String getCccd() {
        return cccd;
    }

    public void setCccd(String cccd) {
        this.cccd = cccd;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
