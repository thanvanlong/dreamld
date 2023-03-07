package com.example.dreamtale.ui.search;

import com.example.dreamtale.base.BaseView;
import com.example.dreamtale.network.dto.Content;

import java.util.List;

public interface SearchView extends BaseView<SearchPresenter> {
    void doSearch(List<Content> list);
}
