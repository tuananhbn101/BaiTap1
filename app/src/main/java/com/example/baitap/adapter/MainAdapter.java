package com.example.baitap.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.baitap.data.User;
import com.example.baitap.databinding.ActivityMainItemBinding;

import java.util.ArrayList;
import java.util.List;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {
    private List<User> users;
    private OnItemClick onItemClick;

    public MainAdapter() {
        users = new ArrayList<>();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        return new ViewHolder(ActivityMainItemBinding.inflate(layoutInflater));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.binding.id.setText(users.get(position).getId());
        holder.binding.name.setText(users.get(position).getName());
        holder.binding.name.setOnClickListener(view -> onItemClick.onItemClickListener(users.get(position)));
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public void setUsers(List<User> userList) {
        this.users = userList;
        notifyDataSetChanged();
    }

    public void setOnItemClick(OnItemClick onItemClick) {
        this.onItemClick = onItemClick;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ActivityMainItemBinding binding;

        public ViewHolder(ActivityMainItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    public interface OnItemClick {
        void onItemClickListener(User user);
    }
}
