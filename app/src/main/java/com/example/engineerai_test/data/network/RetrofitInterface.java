package com.example.engineerai_test.data.network;


import com.example.engineerai_test.data.user.response.Response;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RetrofitInterface {

    @GET("users")
    Call<Response> userList(@Query("offset") long offset, @Query("limit") int limit);
}
