package com.example.baitap;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.baitap.adapter.MainAdapter;
import com.example.baitap.data.User;
import com.example.baitap.databinding.ActivityMainBinding;
import com.example.baitap.viewmodel.MainViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private MainViewModel mainViewModel;
    private MainAdapter adapter;
    private boolean loading = true;
    private int pastVisibleItems;
    private int visibleItemCount;
    private int totalItemCount;
    private List<User> users;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        init();
    }

    private void init() {
        users = new ArrayList<>();

        adapter = new MainAdapter();
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        binding.users.setLayoutManager(layoutManager);
        binding.users.setAdapter(adapter);

        binding.users.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy > 0) {
                    visibleItemCount = layoutManager.getChildCount();
                    totalItemCount = layoutManager.getItemCount();
                    pastVisibleItems = layoutManager.findFirstVisibleItemPosition();

                    if (loading) {
                        if ((visibleItemCount + pastVisibleItems) >= totalItemCount) {
                            loading = false;
                            mainViewModel.requestUsers();
                            binding.loading.setVisibility(View.VISIBLE);
                            loading = true;
                        }
                    }
                }
            }
        });

        binding.refresh.setOnRefreshListener(() -> {
            users.clear();
            mainViewModel.requestUsers();
        });

        mainViewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        mainViewModel.getUsers().observe(this, usersList -> {
            users.addAll(usersList);
            adapter.setUsers(users);
            binding.refresh.setRefreshing(false);
            binding.loading.setVisibility(View.GONE);
        });

        binding.request.setOnClickListener(view -> mainViewModel.requestUsers());

        adapter.setOnItemClick(user -> {
            Intent intent = new Intent(getApplicationContext(), UserDetailActivity.class);
            intent.putExtra("user", user);
            startActivity(intent);
        });
    }

}