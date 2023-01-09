package com.example.dreamtale.ui.mediaplayer;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.dreamtale.R;
import com.example.dreamtale.base.BaseFragment;
import com.example.dreamtale.common.adater.ContentAdapter;
import com.example.dreamtale.common.constant.Constant;
import com.example.dreamtale.common.dialog.YesNoDialog;
import com.example.dreamtale.common.fragment.PackagePaymentFragment;
import com.example.dreamtale.common.view.HorizontalItemDecoration;
import com.example.dreamtale.network.dto.Box;
import com.example.dreamtale.network.dto.Comment;
import com.example.dreamtale.network.dto.Content;
import com.example.dreamtale.network.dto.ContentDTO;
import com.example.dreamtale.ui.home.HomeActivity;
import com.example.dreamtale.ui.home.HomeBoxView;
import com.example.dreamtale.ui.home.HomePresenter;
import com.example.dreamtale.utils.CompatibilityUtils;
import com.facebook.shimmer.ShimmerFrameLayout;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import butterknife.BindView;

public class DetailFragment extends BaseFragment<MediaControlPresenter, HomeActivity> implements MediaControlView, SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.rcy_related)
    RecyclerView rcyContentRelated;
    @BindView(R.id.tv_desc)
    TextView tvDesc;
    @BindView(R.id.btn_read_more)
    Button btnReadMore;
    @BindView(R.id.shimmer_view)
    ShimmerFrameLayout shimmerFrameLayout;
    @BindView(R.id.swipe_refresh_layout)
    SwipeRefreshLayout swipeRefreshLayout;
    ContentAdapter contentAdapter;
    private Content content;
    private int currentPage;
    private List<Content> contentList;
    private boolean isLoading = false;
    private int totalPage = -1;
    private Box.Type type;

    private static DetailFragment instance;

    public static DetailFragment getInstance() {
        return instance;
    }

    public static void setInstance(DetailFragment instance) {
        DetailFragment.instance = instance;
    }

    public synchronized static DetailFragment newInstance() {
        DetailFragment fragment = new DetailFragment();
        instance = fragment;
        return fragment;
    }

    public List<Content> getContentList() {
        return contentList;
    }

    public void setContentList(List<Content> contentList) {
        this.contentList = contentList;
    }

    public Box.Type getType() {
        return type;
    }

    public void setType(Box.Type type) {
        this.type = type;
    }

    public Content getContent() {
        return content;
    }

    public void setContent(Content content) {
        this.content = content;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_story_detail;
    }

    @Override
    public void onPrepareLayout() {
        Bundle bundle = getArguments();
        swipeRefreshLayout.setOnRefreshListener(this);
        content = (Content) bundle.getSerializable(Constant.Extras.DATA);
        type = (Box.Type) bundle.getSerializable(Constant.Extras.TYPE);
        HomeActivity.getInstance().setType(type);
        tvDesc.setText(content.getDescription());
        tvDesc.setMovementMethod(new ScrollingMovementMethod());

        if (content != null) {
            loadData((int) content.getId());
        }


        rcyContentRelated.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                GridLayoutManager layoutManager = (GridLayoutManager) rcyContentRelated.getLayoutManager();

                int v1 = layoutManager.getChildCount();
                int v2 = layoutManager.getItemCount();
                int v3 = layoutManager.findFirstCompletelyVisibleItemPosition();
//                Log.e(TAG, "onScrolled: ", );
                if (v1 + v3 > v2 && currentPage <= totalPage) {
                    currentPage ++;
                    isLoading = true;
                    loadMoreData((int) content.getId());
                }
            }
        });
    }

    public void loadData(int id) {
        getPresenter().getContentList(id, 10, 1);
    }

    public void loadMoreData(int id) {
        getPresenter().getContentList(id, 10, currentPage);
    }

    @Override
    public MediaControlPresenter onCreatePresenter() {
        return new MediaControlPresenterImpl(this);
    }


    @Override
    public void loadListContent(ContentDTO<Content> data) {
        swipeRefreshLayout.setRefreshing(false);
        Log.e("longtv", "loadListContent: " );
        if (data == null ) {
            return;
        }

        if (contentList == null) {
            contentList = new ArrayList<>();
            contentList.addAll(data.getContentList());
            setContentAdapter();
        }
        shimmerFrameLayout.setVisibility(View.GONE);
        contentList.addAll(data.getContentList());
        Set<Content> contentSet = new HashSet<>(contentList);
        contentList = new ArrayList<>(contentSet);

        contentAdapter.notifyDataSetChanged();

        if (totalPage < 0) {
            totalPage = data.getMetaData().getTotalPages();
        }
    }

    @Override
    public void loadComment(ContentDTO<Comment> data) {

    }

    @Override
    public void doCommentSuccess(Comment data) {

    }

    @Override
    public void popupMessage() {
        shimmerFrameLayout.setVisibility(View.GONE);
        YesNoDialog yesNoDialog = new YesNoDialog();
        yesNoDialog.setListener(new YesNoDialog.ItemClickListener() {
            @Override
            public void btnYesClick() {
                Bundle bundle = new Bundle();
                bundle.putBoolean(Constant.Extras.CAN_SEARCH, false);
                bundle.putBoolean(Constant.Extras.TOOL_TITLE, true);
                bundle.putBoolean(Constant.Extras.NAVIGATION, false);
                bundle.putString(Constant.Extras.TITLE, "Buy package");
                HomeActivity.getInstance().addFragment(R.id.frg_common_content, new PackagePaymentFragment(), bundle, true, PackagePaymentFragment.class.getSimpleName());
                yesNoDialog.dismiss();
            }

            @Override
            public void btnNoClick() {
                yesNoDialog.dismiss();
            }
        });

        yesNoDialog.init(getViewContext(), "Can mua goi de xem");
        yesNoDialog.show(getViewContext().getSupportFragmentManager(), null);
    }

    public void setContentAdapter() {
        Log.e("longtv", "setContentAdapter: " + contentList.size() );
        contentAdapter = new ContentAdapter(contentList, getViewContext());
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getViewContext(), 2);
        rcyContentRelated.setLayoutManager(layoutManager);
        rcyContentRelated.addItemDecoration(new HorizontalItemDecoration(CompatibilityUtils.getItemSpacing(getViewContext())));
        rcyContentRelated.setAdapter(contentAdapter);
    }

    @Override
    public void onRefresh() {
        Log.e("longtv", "onRefresh: " );
        loadData((int) content.getId());
    }
}
