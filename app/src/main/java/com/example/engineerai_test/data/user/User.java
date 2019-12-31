package com.example.engineerai_test.data.user;

import android.content.Context;

import com.example.engineerai_test.R;
import com.example.engineerai_test.data.ApiCompleted;
import com.example.engineerai_test.data.network.ServiceGenerator;
import com.example.engineerai_test.data.user.request.UserListRequest;
import com.example.engineerai_test.data.user.response.Response;

import org.jetbrains.annotations.NotNull;

import retrofit2.Call;
import retrofit2.Callback;

public class User {

    public static final int USER_LIST_PAGE_LIMIT = 10;

    public void getUserList(Context mContex, UserListRequest userListRequest, ApiCompleted apiCompleted){

       Call<Response> call =  ServiceGenerator.getRetrofirService().userList(userListRequest.getLimit() * (userListRequest.getPageNo() -1) ,  userListRequest.getLimit());
        call.enqueue(new Callback<Response>() {
            @Override
            public void onResponse(@NotNull Call<Response> call, @NotNull retrofit2.Response<Response> response) {
                if(response.isSuccessful()){
                    if(response.body() != null) {
                        if (response.body().isStatus()) {
                            apiCompleted.onApiCallCompleted(true, response.body().getData());
                        } else {
                            apiCompleted.onApiCallCompleted(false, response.body().getMessage());
                        }
                    }else{
                        apiCompleted.onApiCallCompleted(false, mContex.getResources().getString(R.string.something_went_wrong_please_try_again));
                    }
                }else{
                    apiCompleted.onApiCallCompleted(false, response.message());
                }

            }

            @Override
            public void onFailure(@NotNull Call<Response> call, @NotNull Throwable t) {
                apiCompleted.onApiCallCompleted(false, t.getLocalizedMessage());
            }
        });
    }
}
