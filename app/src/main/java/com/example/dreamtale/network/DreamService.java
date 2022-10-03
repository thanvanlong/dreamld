package com.example.dreamtale.network;

import com.example.dreamtale.network.dto.AuthRequestBody;
import com.example.dreamtale.network.dto.ResponseDTO;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface DreamService {
    @POST("/api/v1/auth/login")
    Call<ResponseDTO<AuthRequestBody>> login(@Body AuthRequestBody authRequestBody);

    @POST("/api/v1/auth/register")
    Call<ResponseDTO<AuthRequestBody>> register(@Body AuthRequestBody authRequestBody);

    @GET("/api/v1/auth/check-phone/{phone}")
    Call<ResponseDTO<AuthRequestBody>> checkPhoneNumber(@Path("phone") String phoneNumber);

}
