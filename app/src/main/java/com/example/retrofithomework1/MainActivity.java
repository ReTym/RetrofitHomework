package com.example.retrofithomework1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements Observer<List<User>> {

    private RecyclerView usersList;
    private UsersAdapter usersAdapter;
    private List<User> users;
    private LiveData<List<User>> liveData;
    private Button btnUpdate;
    private BackgroundThread backgroundThread = new BackgroundThread();

    private View.OnClickListener OnUpdateClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            backgroundThread.start();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.users_list);

        users = new ArrayList<>();

        usersList = findViewById(R.id.rv_users);
        btnUpdate = findViewById(R.id.btn_update_user);

        btnUpdate.setOnClickListener(OnUpdateClickListener);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        usersList.setLayoutManager(layoutManager);

        usersAdapter = new UsersAdapter();
        usersList.setAdapter(usersAdapter);

        liveData = backgroundThread.getLiveData();
        liveData.observe(this, this);
    }

    @Override
    public void onChanged(List<User> users) {
        usersAdapter.setData(users);
    }
}