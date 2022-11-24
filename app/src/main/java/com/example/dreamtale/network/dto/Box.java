package com.example.dreamtale.network.dto;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Box {
    private long id;
    @SerializedName("list")
    private List<Content> contentList;
    @SerializedName("type")
    private Box.Type type;
    @SerializedName("name")
    private String name;

    public Box() {
    }

    public Box(List<Content> contentList, Type type, String name) {
        this.contentList = contentList;
        this.type = type;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<Content> getContentList() {
        return contentList;
    }

    public void setContentList(List<Content> contentList) {
        this.contentList = contentList;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public enum Type {
        AUDIO_BOOK,
        MUSIC,
        AUDIO_LISTENING,
        MUSIC_LISTENING,
        AUTHOR,
        AUDIO_DETAIL

    }

    @Override
    public String toString() {
        return "Box{" +
                "id=" + id +
                ", contentList=" + contentList +
                ", type=" + type +
                ", name='" + name + '\'' +
                '}';
    }
}
