package com.example.common.network.user;


import com.example.common.callback.IntCallback;
import com.example.common.callback.StringCallBack;
import com.example.common.callback.UserCallback;
import com.example.common.network.JsonRootBean;
import com.example.common.network.RetrofitUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserUtil {

    private static UserService service;

    private static UserService getService() {
        if (service == null) {
            synchronized (UserService.class) {
                if (service == null) {
                    service = RetrofitUtils.getInstance().create(UserService.class);
                }
            }
        }
        return service;
    }

    public static void login(String username, String password, StringCallBack callBack) {
        getService().login(username, password).enqueue(new Callback<JsonRootBean<String>>() {
            @Override
            public void onResponse(Call<JsonRootBean<String>> call, Response<JsonRootBean<String>> response) {
                callBack.string(response.body().getData());
            }

            @Override
            public void onFailure(Call<JsonRootBean<String>> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }


    public static void getByUid(String toke, UserCallback callback) {
        getService().getByUid(toke).enqueue(new Callback<JsonRootBean<User>>() {
            @Override
            public void onResponse(Call<JsonRootBean<User>> call, Response<JsonRootBean<User>> response) {
                callback.user(response.body().getData());
            }

            @Override
            public void onFailure(Call<JsonRootBean<User>> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    public static void changeInfo(String toke, User user, IntCallback callback) {
        getService().changeInfo(toke, user).enqueue(new Callback<JsonRootBean<Void>>() {
            @Override
            public void onResponse(Call<JsonRootBean<Void>> call, Response<JsonRootBean<Void>> response) {
                callback.code(response.body().getState());
            }

            @Override
            public void onFailure(Call<JsonRootBean<Void>> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    public static void changePassword(String toke, String oldPassword, String newPassword, IntCallback callback) {
        getService().changePassword(toke, oldPassword, newPassword).enqueue(new Callback<JsonRootBean<Void>>() {
            @Override
            public void onResponse(Call<JsonRootBean<Void>> call, Response<JsonRootBean<Void>> response) {
                callback.code(response.body().getState());
            }

            @Override
            public void onFailure(Call<JsonRootBean<Void>> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    public static void reg(String toke,  User user,  IntCallback callback) {
        getService().reg(toke, user).enqueue(new Callback<JsonRootBean<Void>>() {
            @Override
            public void onResponse(Call<JsonRootBean<Void>> call, Response<JsonRootBean<Void>> response) {
                callback.code(response.body().getState());
            }

            @Override
            public void onFailure(Call<JsonRootBean<Void>> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
}
