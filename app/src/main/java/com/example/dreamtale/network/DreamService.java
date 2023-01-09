package com.example.dreamtale.network;

import com.example.dreamtale.network.dto.AuthRequestBody;
import com.example.dreamtale.network.dto.Box;
import com.example.dreamtale.network.dto.Category;
import com.example.dreamtale.network.dto.CategoryDTO;
import com.example.dreamtale.network.dto.Comment;
import com.example.dreamtale.network.dto.Content;
import com.example.dreamtale.network.dto.ContentDTO;
import com.example.dreamtale.network.dto.DeviceInfo;
import com.example.dreamtale.network.dto.LogHistory;
import com.example.dreamtale.network.dto.Package;
import com.example.dreamtale.network.dto.PaymentRequest;
import com.example.dreamtale.network.dto.PaymentResponse;
import com.example.dreamtale.network.dto.ResponseDTO;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface DreamService {
    @POST("/api/v1/users/login")
    Call<ResponseDTO<AuthRequestBody>> login(@Body AuthRequestBody authRequestBody);

    @POST("/api/v1/users/register")
    Call<ResponseDTO<AuthRequestBody>> register(@Body AuthRequestBody authRequestBody);

    @GET("/api/v1/users/check-phone/{phone}")
    Call<ResponseDTO<AuthRequestBody>> checkPhoneNumber(@Path("phone") String phoneNumber);

    @GET("/api/v1/device")
    Call<ResponseDTO<List<DeviceInfo>>> getListDeviceLogged();

    @POST("/api/v1/device/logout-device")
    Call<ResponseDTO<Object>> logout(@Body List<DeviceInfo> deviceInfo);

    @GET("/api/v1/genre")
    Call<ResponseDTO<ContentDTO<Category>>> getListCategory(@Query("pageSize") int pageSize, @Query("pageNumber") int pageNumber);

//    @POST("/api/v1/users/choose-catagory")
    @GET("/api/v1/audio-book-ep/{id}/list-ep")
    Call<ResponseDTO<ContentDTO<Content>>> getListContent(@Path("id") int id ,@Query("pageSize") int pageSize, @Query("pageNumber") int pageNumber);

    @GET("/api/v1/tale/home")
    Call<ResponseDTO<ContentDTO<Box>>> getHomeData(@Query("pageSize") int pageSize, @Query("pageNumber") int pageNumber);

    @GET("/api/v1/comment")
    Call<ResponseDTO<ContentDTO<Comment>>> getComment(@Query("pageSize") int pageSize, @Query("pageNumber") int pageNumber, @Query("id") int id, @Query("type") int type);

    @POST("/api/v1/comment")
    Call<ResponseDTO<Comment>> doComment(@Body Comment comment);

//    @POST("/api/v1/history/audio-book/save")
//    Call

    @POST("/api/v1/history/audio-book/save")
    Call<ResponseDTO<LogHistory>> logHistory(@Body LogHistory logHistory);

    @GET("/api/v1/audio-book/search")
    Call<ResponseDTO<ContentDTO<Content>>> search(@Query("search") String search, @Query("searchFields[]") String[] searchFields);

    @GET("/api/v1/package")
    Call<ResponseDTO<ContentDTO<Package>>> getPackages();

    @POST("/api/v1/payment/create")
    Call<ResponseDTO<PaymentResponse>> createOrder(@Body PaymentRequest paymentRequest);

    @POST("/api/v1/payment")
    Call<ResponseDTO<PaymentResponse>> verifyOrder(@Body PaymentRequest paymentRequest);
}
