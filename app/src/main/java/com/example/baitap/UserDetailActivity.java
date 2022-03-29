package com.example.baitap;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.baitap.data.User;
import com.example.baitap.databinding.ActivityUserDetailBinding;

public class UserDetailActivity extends AppCompatActivity {
    private ActivityUserDetailBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityUserDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        init();
    }

    private void init() {
        Intent intent = getIntent();
        if (intent == null) {
            return;
        } else if (intent.getSerializableExtra("user") == null) {
            return;
        } else {
            User user = (User) intent.getSerializableExtra("user");
            binding.fullNameLabel.setText(user.getName());
            binding.addressLabel.setText(user.getAddress());
            binding.ageLabel.setText(user.getAge() + "");
        }
    }
}