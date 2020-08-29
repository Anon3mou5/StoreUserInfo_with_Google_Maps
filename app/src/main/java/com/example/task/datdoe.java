package com.example.task;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface datdoe {

    @Query("SELECT * FROM PEOPLE")
    List<people> getelements();

    @Query("SELECT * FROM PEOPLE WHERE email LIKE :eml AND password LIKE :pass")
    people presentornot(String eml, String pass);

    @Insert
    void insertdata(people obj);

    @Update
    void update(people oldobj, people newobj);

    @Delete
    void delete(people obj);
}
