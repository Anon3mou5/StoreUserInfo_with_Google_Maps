package com.example.task;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

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

    public people( String name, String email, String phno, String password) {
        this.name = name;
        this.email = email;
        this.phno = phno;
        this.password = password;
    }
}
