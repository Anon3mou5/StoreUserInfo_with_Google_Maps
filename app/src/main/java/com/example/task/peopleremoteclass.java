package com.example.task;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
@Database(entities = {people.class},version = 1)
public abstract class peopleremoteclass extends RoomDatabase {

    public abstract  datdoe doe();

    public  static peopleremoteclass Object;

    public static   peopleremoteclass getinstance(Context c)
    {
        if(Object==null)
        {
            synchronized (peopleremoteclass.class)
            {
                if(Object==null)
                {
                    Object = Room.databaseBuilder(c.getApplicationContext(),peopleremoteclass.class,"roomdb").build();
                }
            }
        }
        return  Object;
    }


}