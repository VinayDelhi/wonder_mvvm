package com.example.wonder.wonder_mvvm.data.network;

import retrofit2.Response;

public interface NetworkCallback<T> {
    String onResponse(NetworkCall<T> call, Response<T> response);

    String onFailure(NetworkCall<T> call, Throwable t);
}
