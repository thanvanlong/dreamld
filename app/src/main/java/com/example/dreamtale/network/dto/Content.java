package com.example.dreamtale.network.dto;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Content implements Serializable {
    @SerializedName("id")
    private long id;
    @SerializedName("audioBook")
    private long audioBook;
    @SerializedName("audioBookEp")
    private long audioBookEp;
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
    private Box.Type type;
    @SerializedName("lasted")
    private Content contentEp;
    @SerializedName("progress")
    private long progress;
    @SerializedName("duration")
    private long duration;
    public Content() {
    }

    public Content(String name, String author, String img) {
        this.name = name;
        this.author = author;
        this.img = img;
    }

    public Box.Type getType() {
        return type;
    }

    public Content getContentEp() {
        return contentEp;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public void setContentEp(Content contentEp) {
        this.contentEp = contentEp;
    }

    public long getProgress() {
        return progress;
    }

    public void setProgress(long progress) {
        this.progress = progress;
    }

    public void setType(Box.Type type) {
        this.type = type;
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

    public long getAudioBook() {
        return audioBook;
    }

    public void setAudioBook(long audioBook) {
        this.audioBook = audioBook;
    }

    public long getAudioBookEp() {
        return audioBookEp;
    }

    public void setAudioBookEp(long audioBookEp) {
        this.audioBookEp = audioBookEp;
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
