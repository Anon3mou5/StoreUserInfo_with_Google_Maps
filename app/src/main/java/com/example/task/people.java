package com.example.task;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.jetbrains.annotations.NotNull;

import java.io.Serializable;

@Entity(tableName = "People")
public class people implements Serializable {
    @PrimaryKey ( autoGenerate = true)
     int id ;
    @ColumnInfo
    private String name;
    @ColumnInfo
    private  String phno;
    @ColumnInfo
    @NotNull
    private String  email;


    @ColumnInfo
    @NotNull
    private String lat;

    @ColumnInfo
    @NotNull
    private String longi;

    @NotNull
    public String getAddress() {
        return address;
    }

    public void setAddress(@NotNull String address) {
        this.address = address;
    }

    @ColumnInfo
    @NotNull
    private String  address;


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

    public String getPhno() {
        return phno;
    }

    public void setPhno(String phno) {
        this.phno = phno;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @ColumnInfo
    private  String password;

    @NotNull
    public String getLat() {
        return lat;
    }

    public void setLat(@NotNull String lat) {
        this.lat = lat;
    }

    @NotNull
    public String getLongi() {
        return longi;
    }

    public void setLongi(@NotNull String longi) {
        this.longi = longi;
    }

    public people(String name, String email, String phno, String password, String address, String lat,String longi) {
        this.name = name;
        this.email = email;
        this.phno = phno;
        this.password = password;
        this.address=address;
        this.lat=lat;
        this.longi= longi;
    }
}
