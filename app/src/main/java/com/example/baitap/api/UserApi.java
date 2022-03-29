package com.example.baitap.api;

import com.example.baitap.data.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface UserApi {
    @GET("user")
    Call<List<User>> requestUsers();
}
