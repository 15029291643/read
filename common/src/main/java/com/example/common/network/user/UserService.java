package com.example.common.network.user;

import com.example.common.network.JsonRootBean;
import com.example.common.network.user.User;

import retrofit2.Call;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface UserService {

    @POST("users/login")
    Call<JsonRootBean<String>> login(@Query("username") String username, @Query("password") String password);


    @POST("users/get_by_uid")
    Call<JsonRootBean<User>> getByUid(@Header("token") String token);

    @POST("users/change_info")
    Call<JsonRootBean<Void>> changeInfo(@Header("token") String token, @Query("user") User user);

    @POST("users/change_password")
    Call<JsonRootBean<Void>> changePassword(@Header("token") String token, @Query("oldPassword") String oldPassword, @Query("newPassword") String newPassword);

    @POST("users/reg")
    Call<JsonRootBean<Void>> reg(@Header("token") String token, @Query("user") User user);
}
