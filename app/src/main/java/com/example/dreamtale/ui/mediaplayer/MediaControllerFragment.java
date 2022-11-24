package com.example.dreamtale.ui.mediaplayer;

import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import androidx.viewpager2.widget.ViewPager2;

import com.example.dreamtale.R;
import com.example.dreamtale.base.BaseFragment;
import com.example.dreamtale.common.constant.Constant;
import com.example.dreamtale.network.dto.Content;
import com.example.dreamtale.ui.home.HomeActivity;
import com.example.dreamtale.ui.home.HomePresenter;

import butterknife.BindView;

public class MediaControllerFragment extends BaseFragment<HomePresenter, HomeActivity>{

    @BindView(R.id.container_media)
    ViewPager2 pager;
    @BindView(R.id.container_info_content)
    LinearLayout containerInfoContent;
    @Override
    public int getLayoutId() {
        return R.layout.fragment_media_controller;
    }

    public void setVisibilityInfoContent(int visibility) {
        containerInfoContent.setVisibility(View.GONE);
    }

    private static MediaControllerFragment instance;

    public static MediaControllerFragment getInstance() {
        return instance;
    }

    public static void setInstance(MediaControllerFragment instance) {
        MediaControllerFragment.instance = instance;
    }

    @Override
    public void onPrepareLayout() {
        instance = this;
        MediaControllerPagerAdapter adapter = new MediaControllerPagerAdapter(getViewContext());
        Content content = (Content) getArguments().getSerializable(Constant.Extras.DATA);
        if (adapter == null) {
            Log.e("longtv", "onPrepareLayout: error" );
            return;
        }

        pager.post(new Runnable() {
            @Override
            public void run() {
                pager.setCurrentItem(1);
            }
        });
        pager.setAdapter(adapter);
        pager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset,
                                       int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);

            }

            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                Log.e("longtv", "onPageScrolled: " + position + " " + containerInfoContent.getVisibility());
                switch (position) {
                    case 0:
                        HomeActivity.getInstance().setupToolBar("Comments", null, null, null, null);
                        setVisibilityInfoContent(View.GONE);
                        break;
                    case 1:
                        HomeActivity.getInstance().setupToolBar(null, content.getCoverImg(), true, null, null);
                        setVisibilityInfoContent(View.VISIBLE);
                        break;
                    case 2:
                        HomeActivity.getInstance().setupToolBar("Các tập", null, null, null, null);
                        setVisibilityInfoContent(View.GONE);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                super.onPageScrollStateChanged(state);
            }
        });


    }

    @Override
    public HomePresenter onCreatePresenter() {
        return null;
    }
}
