package com.example.dreamtale.ui.search;

import android.util.Log;

import com.example.dreamtale.base.BaseCallback;
import com.example.dreamtale.base.BasePresenterImpl;
import com.example.dreamtale.network.ServiceBuilder;
import com.example.dreamtale.network.dto.Content;
import com.example.dreamtale.network.dto.ContentDTO;

public class SearchPresenterImpl extends BasePresenterImpl<SearchView> implements SearchPresenter {
    public SearchPresenterImpl(SearchView view) {
        super(view);
    }

    @Override
    public void doSearch(String data) {
        String[] field = {"name"};
        ServiceBuilder.getService().search(data, field).enqueue(new BaseCallback<ContentDTO<Content>>() {
            @Override
            public void onError(String errorCode, String errorMessage) {
                Log.e("longtv", "onError: searcccc " );
            }

            @Override
            public void onResponse(ContentDTO<Content> data) {
                ContentDTO<Content> dto = data;
                Log.e("longtv", "onResponse: searccccc " + dto.getContentList().size() );
                mView.doSearch(data.getContentList());
            }
        });
    }
}
