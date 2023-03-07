package com.example.dreamtale.network.dto;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Comment implements Serializable {
    @SerializedName("id")
    private int id;
    @SerializedName("star")
    private int star;
    @SerializedName("content")
    private String comment;
    @SerializedName("createdAt")
    private String localDateTime;
    @SerializedName("user")
    private AuthRequestBody au;
    private int type;



    public Comment(int id, int star, String comment, String localDateTime, AuthRequestBody au) {
        this.id = id;
        this.star = star;
        this.comment = comment;
        this.localDateTime = localDateTime;
        this.au = au;
    }

    public Comment() {
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStar() {
        return star;
    }

    public void setStar(int star) {
        this.star = star;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(String localDateTime) {
        this.localDateTime = localDateTime;
    }

    public AuthRequestBody getAu() {
        return au;
    }

    public void setAu(AuthRequestBody au) {
        this.au = au;
    }
}
