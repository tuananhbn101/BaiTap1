package com.example.baitap.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface UserDao {
    @Query("SELECT DISTINCT * FROM User")
    LiveData<List<User>> getUsers();

    @Query("DELETE FROM User")
    void deleteAll();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertUsers(List<User> users);
}
