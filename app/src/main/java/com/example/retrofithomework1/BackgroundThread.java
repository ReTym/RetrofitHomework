package com.example.retrofithomework1;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

public class BackgroundThread extends Thread {
    MutableLiveData<List<User>> liveData = new MutableLiveData<>();

    public LiveData<List<User>> getLiveData() {
        return liveData;
    }

    @Override
    public void run() {
        List<User> users = new ArrayList<>();
        for(int i = 0; i < 10; i++) {
            Call<List<User>> call = App.getApi().getUsers(i+1);
            try {
                Response<List<User>> response = call.execute();
                users.addAll(response.body());
                liveData.postValue(users);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}



