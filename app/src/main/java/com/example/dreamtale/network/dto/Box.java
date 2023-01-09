package com.example.dreamtale.network.dto;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
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
        @SerializedName("recommend_audio_book")
        AUDIO_BOOK,
        @SerializedName("music")
        MUSIC,
        @SerializedName("history_audio_book")
        AUDIO_LISTENING,
        @SerializedName("aaa")
        MUSIC_LISTENING,
        @SerializedName("recommend_author")
        AUTHOR,
        @SerializedName("sss")
        AUDIO_DETAIL,
        @SerializedName("favourite_auido_book")
        FAVOURITE_AUDIO_BOOK

    }

    public static List<Box> removeBoxEmpty(@NonNull List<Box> data) {
        List<Box> boxes = new ArrayList<>();
        for (Box box :
                data) {
            if (box.getContentList().size() != 0) {
                boxes.add(box);
            }
        }

        return boxes;
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
