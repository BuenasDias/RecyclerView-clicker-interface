package com.shepko.brodcastrecievertest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import com.shepko.brodcastrecievertest.data.entity.User;
import com.shepko.brodcastrecievertest.ui.adapters.RecyclerAdapterUser;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TextView mTextNameUser;
    private RecyclerView mRecyclerView;
    private List<User> users = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();

        RecyclerAdapterUser.OnStateClickListener stateClickListener =
                (user, position) -> mTextNameUser.setText(user.getName());

        RecyclerAdapterUser adapterUser =
                new RecyclerAdapterUser(this, users, stateClickListener);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);


        mRecyclerView.setItemViewCacheSize(users.size());
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(adapterUser);

        // TODO create button with logic methods from adapter (clear and setUsers)

    }

    private void initData() {
        users.add(new User("Max", "31"));
        users.add(new User("Tom", "21"));
        users.add(new User("Sara", "41"));
        users.add(new User("Mike", "54"));
        users.add(new User("Lera", "63"));
        users.add(new User("Lena", "21"));
        users.add(new User("Katia", "23"));
        users.add(new User("Tomas", "56"));
        users.add(new User("Igor", "12"));
        users.add(new User("Petr", "32"));
        users.add(new User("Tatiana", "38"));
        users.add(new User("Nick", "31"));
    }

    private void initView() {
        mTextNameUser = findViewById(R.id.text_name_user);
        mRecyclerView = findViewById(R.id.recycler_users);
    }
}