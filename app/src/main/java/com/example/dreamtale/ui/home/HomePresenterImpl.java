package com.example.dreamtale.ui.home;



import com.example.dreamtale.base.BaseCallback;
import com.example.dreamtale.base.BasePresenterImpl;
import com.example.dreamtale.network.ServiceBuilder;
import com.example.dreamtale.network.dto.Box;
import com.example.dreamtale.network.dto.ContentDTO;

public class HomePresenterImpl extends BasePresenterImpl<HomeBoxView> implements HomePresenter{
    public HomePresenterImpl(HomeBoxView view) {
        super(view);
    }

    @Override
    public void getHomeData(int pageSize, int pageNumber) {
        ServiceBuilder.getService().getHomeData(pageSize, pageNumber).enqueue(new BaseCallback<ContentDTO<Box>>() {
            @Override
            public void onError(String errorCode, String errorMessage) {
                mView.loadDataFailed();
            }

            @Override
            public void onResponse(ContentDTO<Box> data) {
                mView.loadHomeBox(data);
            }
        });
    }
}
