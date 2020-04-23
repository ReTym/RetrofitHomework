package com.example.retrofithomework1;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface JsonPlaceHolderApi {
    @GET("/posts")
    Call<List<User>> getUsers(@Query("userId") int count);
}
