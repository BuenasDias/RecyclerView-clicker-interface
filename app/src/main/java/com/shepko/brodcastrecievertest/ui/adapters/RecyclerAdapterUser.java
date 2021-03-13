package com.shepko.brodcastrecievertest.ui.adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.shepko.brodcastrecievertest.R;
import com.shepko.brodcastrecievertest.data.entity.User;

import java.util.List;

public class RecyclerAdapterUser extends RecyclerView.Adapter<RecyclerAdapterUser.ViewHolder> {

    public interface OnStateClickListener {
        void onStateClick(User user, int position);
    }

    private final List<User> users;
    private final OnStateClickListener onClickListener;
    private final LayoutInflater inflater;

    public RecyclerAdapterUser(Context context, List<User> users, OnStateClickListener onClickListener) {
        this.onClickListener = onClickListener;
        this.users = users;
        this.inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.inflater_recycler_users, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        User user = users.get(position);
        holder.mTextUserName.setText(user.getName());
        holder.mTextUserAge.setText(user.getAge());
        holder.itemView.setOnClickListener(view -> {
            onClickListener.onStateClick(user, position);
        });
    }

    public void setUsers(List<User> listUsers) {
        users.addAll(listUsers);
        notifyDataSetChanged();
    }

    public void clearItems() {
        users.clear();
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView mTextUserName;
        private final TextView mTextUserAge;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mTextUserName = itemView.findViewById(R.id.text_name_user_recycler);
            mTextUserAge = itemView.findViewById(R.id.text_age_user_recycler);
        }
    }
}
