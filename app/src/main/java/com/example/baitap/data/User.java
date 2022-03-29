package com.example.baitap.data;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.baitap.util.Constant;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

@Entity
public class User implements Serializable {
    @PrimaryKey
    @NonNull
    @SerializedName("id")
    @Expose
    private String id;

    @ColumnInfo(name = Constant.COLUMN_USER_NAME)
    @SerializedName("name")
    @Expose
    private String name;

    @ColumnInfo(name = Constant.COLUMN_USER_AGE)
    @SerializedName("age")
    @Expose
    private int age;

    @ColumnInfo(name = Constant.COLUMN_USER_ADDRESS)
    @SerializedName("address")
    @Expose
    private String address;

    public User(String id, String name, int age, String address) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.address = address;
    }

    @NonNull
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getAddress() {
        return address;
    }
}
