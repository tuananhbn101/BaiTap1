package com.example.baitap.data;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.baitap.util.Constant;

@Database(entities = {User.class}, version = UserDatabase.DATABASE_VERSION)
public abstract class UserDatabase extends RoomDatabase {
    public static final int DATABASE_VERSION = 1;
    private static UserDatabase userDatabase;

    public static UserDatabase getInstance(Context context) {
        if (userDatabase == null) {
            userDatabase = Room.databaseBuilder(context, UserDatabase.class, Constant.DATABASE_NAME)
                    .allowMainThreadQueries()
                    .build();
        }
        return userDatabase;
    }

    public abstract UserDao userDAO();
}
