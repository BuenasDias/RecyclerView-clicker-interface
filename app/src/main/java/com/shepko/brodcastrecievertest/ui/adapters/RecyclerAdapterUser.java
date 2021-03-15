package com.shepko.brodcastrecievertest.ui.adapters;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.shepko.brodcastrecievertest.R;
import com.shepko.brodcastrecievertest.data.entity.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

import io.reactivex.internal.subscribers.SinglePostCompleteSubscriber;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;


public class RecyclerAdapterUser extends RecyclerView.Adapter<RecyclerAdapterUser.ViewHolder> {
    private int clickedPosition = -1;


    public interface OnStateClickListener {
        void onStateClick(User user, int position);
    }

    private final List<User> users;
    private final OnStateClickListener onClickListener;
    private final LayoutInflater inflater;
    Context context;

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
        holder.itemView.setBackgroundColor(Color.parseColor("#FFFFFF"));

        holder.itemView.setOnClickListener(view -> {

            if(clickedPosition != position){

                Log.d("TAG", ">>>>>Сработало");

                clickedPosition = position;
                notifyItemChanged(position, 1);
                onClickListener.onStateClick(user, position);
            }

        });
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position, @NonNull List<Object> payloads) {

        if (!payloads.isEmpty()) {
            switch ((Integer) payloads.get(0)) {
                case 1:
                    for (int i = 0; i < getItemCount(); i++) {
                        if(i == position){
                            holder.itemView.setBackgroundColor(Color.parseColor("#343434"));
                        }else{
                            int finalI = i;
                            Completable.complete()
                                    .observeOn(AndroidSchedulers.mainThread())
                                    .subscribe(() -> {
                                        notifyItemChanged(finalI,2);
                                    });
                        }
                    }
                    break;
                case 2:
                    holder.itemView.setBackgroundColor(Color.parseColor("#FFFFFF"));
                    break;
                default:
                    break;
            }
        } else {
            super.onBindViewHolder(holder, position, payloads);
        }
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
