package com.example.baitap.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.baitap.api.RetrofitClient;
import com.example.baitap.api.UserApi;
import com.example.baitap.data.User;
import com.example.baitap.data.UserDao;
import com.example.baitap.data.UserDatabase;

import java.util.List;

import retrofit2.Call;

public class Repository {
    private UserDatabase userDatabase;
    private LiveData<List<User>> users;
    private UserDao userDao;
    private UserApi userApi;

    public Repository(Application application) {
        userDatabase = UserDatabase.getInstance(application);
        userDao = userDatabase.userDAO();
        users = userDao.getUsers();
        userApi = RetrofitClient.getInstance().create(UserApi.class);
    }

    public LiveData<List<User>> getUsers() {
        return users;
    }

    public void insertUsers(List<User> users) {
        userDao.insertUsers(users);
    }

    public void delete(){
        userDao.deleteAll();
    }

    public Call<List<User>> requestUsers(){
        return userApi.requestUsers();
    }
}
