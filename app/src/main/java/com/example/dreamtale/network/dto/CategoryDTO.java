package com.example.dreamtale.network.dto;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CategoryDTO {
    @SerializedName("results")
    private List<Category> categoryList;
    @SerializedName("metaData")
    private MetaData metaData;

    public List<Category> getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(List<Category> categoryList) {
        this.categoryList = categoryList;
    }

    public MetaData getMetaData() {
        return metaData;
    }

    public void setMetaData(MetaData metaData) {
        this.metaData = metaData;
    }
}
