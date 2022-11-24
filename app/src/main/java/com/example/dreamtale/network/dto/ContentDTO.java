package com.example.dreamtale.network.dto;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ContentDTO<T>{
    @SerializedName("results")
    private List<T> contentList;
    @SerializedName("metaData")
    private MetaData metaData;

    public List<T> getContentList() {
        return contentList;
    }

    public void setContentList(List<T> contentList) {
        this.contentList = contentList;
    }

    public MetaData getMetaData() {
        return metaData;
    }

    public void setMetaData(MetaData metaData) {
        this.metaData = metaData;
    }
}
