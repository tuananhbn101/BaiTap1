package com.example.baitap.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.baitap.data.User;
import com.example.baitap.repository.Repository;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainViewModel extends AndroidViewModel {
    private Repository repository;
    private MutableLiveData<List<User>> users;

    public MainViewModel(@NonNull Application application) {
        super(application);
        repository = new Repository(application);
        users = new MutableLiveData<>();
    }

    public MutableLiveData<List<User>> getUsers() {
        return users;
    }

    public void insertUsers(List<User> users) {
        repository.insertUsers(users);
    }

    public void requestUsers() {
        repository.requestUsers().enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                if (response.body() == null) {
                    return;
                } else {
                    insertUsers(response.body());
                    users.postValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {

            }
        });
    }

    public void deleteAll(){
        repository.delete();
    }
}
