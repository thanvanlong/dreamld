package com.example.dreamtale.network.dto;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Content implements Serializable {
    @SerializedName("id")
    private long id;
    @SerializedName("name")
    private String name;
    private String author;
    private String img;
    @SerializedName("desc")
    private String description;
    @SerializedName("ep")
    private DataStream dataStream;
    @SerializedName("image")
    private String coverImg;
    public Content() {
    }

    public Content(String name, String author, String img) {
        this.name = name;
        this.author = author;
        this.img = img;
    }

    public String getDescription() {
        return description;
    }

    public DataStream getDataStream() {
        return dataStream;
    }

    public void setDataStream(DataStream dataStream) {
        this.dataStream = dataStream;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCoverImg() {
        return coverImg;
    }

    public void setCoverImg(String coverImg) {
        this.coverImg = coverImg;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
