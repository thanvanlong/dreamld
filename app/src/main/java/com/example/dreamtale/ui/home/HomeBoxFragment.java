package com.example.dreamtale.ui.home;

import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.dreamtale.R;
import com.example.dreamtale.base.BaseFragment;
import com.example.dreamtale.network.dto.Box;
import com.example.dreamtale.network.dto.Content;
import com.example.dreamtale.network.dto.ContentDTO;
import com.example.dreamtale.utils.PrefManager;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.internal.LinkedTreeMap;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class HomeBoxFragment extends BaseFragment<HomePresenter, HomeActivity> implements HomeBoxView, SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.shimmer_view)
    protected ShimmerFrameLayout shimmerLayout;
    @BindView(R.id.rcy_home_content)
    RecyclerView rcyContent;
    @BindView(R.id.swipe_refresh_layout)
    SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.btn_retry)
    Button btnRetry;
    private HomeBoxAdapter homeBoxAdapter;
    private List<Box> boxes;
    private boolean isRefreshing = false;
    private int currentPage = 1;
    private int totalPage = 0;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    public void onPrepareLayout() {
        Log.e("anth", PrefManager.getHomeData(getViewContext()) + " size?");
//        if (HomeActivity.getInstance() != null && HomeActivity.getInstance().getDataHomeBox() == null) {
            loadData();
//        } else {
//            loadHomeBox(HomeActivity.getInstance().getDataHomeBox());
//        }
        swipeRefreshLayout.setOnRefreshListener(this);
        rcyContent.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                GridLayoutManager layoutManager = (GridLayoutManager) rcyContent.getLayoutManager();

                int v1 = layoutManager.getChildCount();
                int v2 = layoutManager.getItemCount();
                int v3 = layoutManager.findFirstCompletelyVisibleItemPosition();

                if (v1 + v3 > v2 && !isRefreshing && currentPage <= totalPage) {
                    currentPage ++;
                    isRefreshing = true;
                    loadMoreData();
                }
            }
        });

        btnRetry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadData();
                btnRetry.setVisibility(View.GONE);
                shimmerLayout.setVisibility(View.VISIBLE);
            }
        });
    }

    public void loadData() {
        getPresenter().getHomeData(10, 1);
    }

    public void loadMoreData() {
//        getPresenter().getHomeData(5, currentPage);
    }

    @Override
    public HomePresenter onCreatePresenter() {
        return new HomePresenterImpl(this);
    }

    @Override
    public void loadHomeBox(ContentDTO<Box> data) {
        shimmerLayout.setVisibility(View.GONE);
        isRefreshing = false;
        swipeRefreshLayout.setRefreshing(false);
        boxes = new ArrayList<>();
        GridLayoutManager layoutManager = new GridLayoutManager(getViewContext(), 1);
        rcyContent.setLayoutManager(layoutManager);
        if (boxes.size() != 0) {
            boxes.clear();
        }
        rcyContent.setVisibility(View.VISIBLE);
        boxes.addAll(Box.removeBoxEmpty(data.getContentList()));

        homeBoxAdapter = new HomeBoxAdapter(getViewContext(), boxes);
        rcyContent.setAdapter(homeBoxAdapter);

        totalPage = data.getMetaData().getTotalPages();
        PrefManager.setHomeData(getViewContext(), data);
    }

    @Override
    public void loadDataFailed() {
        shimmerLayout.setVisibility(View.GONE);
        btnRetry.setVisibility(View.VISIBLE);
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onRefresh() {
        Log.e("longtv", "onRefresh: 1" );
        if (isRefreshing) {
            return;
        }
        Log.e("longtv", "onRefresh: " );
        rcyContent.setVisibility(View.GONE);
        isRefreshing = true;
        btnRetry.setVisibility(View.GONE);
        shimmerLayout.setVisibility(View.VISIBLE);
        loadData();

    }

    @Override
    public void onStop() {
        super.onStop();
        Log.e("longtv", "onStop: " );
//        PrefManager.clearHomeCache(getViewContext());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
//        PrefManager.clearHomeCache(getViewContext());
    }
}
