package com.example.dreamtale.network.dto;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ContentSplash implements Serializable {
    @SerializedName("id")
    private int id;
    @SerializedName("imgBg")
    private String imgBg;
    @SerializedName("header")
    private String header;
    @SerializedName("content")
    private String content;
}
