package com.example.dreamtale.ui.mediaplayer;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.viewpager2.widget.ViewPager2;

import com.example.dreamtale.R;
import com.example.dreamtale.base.BaseFragment;
import com.example.dreamtale.common.constant.Constant;
import com.example.dreamtale.common.service.MediaNotificationService;
import com.example.dreamtale.network.dto.Box;
import com.example.dreamtale.network.dto.Content;
import com.example.dreamtale.ui.home.HomeActivity;
import com.example.dreamtale.ui.home.HomePresenter;

import butterknife.BindView;

public class MediaControllerFragment extends BaseFragment<HomePresenter, HomeActivity>{

    @BindView(R.id.container_media)
    ViewPager2 pager;
    @BindView(R.id.container_info_content)
    LinearLayout containerInfoContent;
    @BindView(R.id.seek_bar)
    SeekBar seekBar;
    @BindView(R.id.tv_time_end)
    TextView tvTimeEnd;
    @BindView(R.id.tv_time_start)
    TextView tvTimeStart;

    public TextView getTvTimeEnd() {
        return tvTimeEnd;
    }

    public void setTvTimeEnd(TextView tvTimeEnd) {
        this.tvTimeEnd = tvTimeEnd;
    }

    public TextView getTvTimeStart() {
        return tvTimeStart;
    }

    public void setTvTimeStart(TextView tvTimeStart) {
        this.tvTimeStart = tvTimeStart;
    }

    private Box.Type type;

    private Content content;

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

    public SeekBar getSeekBar() {
        return seekBar;
    }

    public void setSeekBar(SeekBar seekBar) {
        this.seekBar = seekBar;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_media_controller;
    }

    public void setVisibilityInfoContent(int visibility) {
        containerInfoContent.setVisibility(visibility);
    }

    private static MediaControllerFragment instance;

    public static MediaControllerFragment getInstance() {
        return instance;
    }

    public static void setInstance(MediaControllerFragment instance) {
        MediaControllerFragment.instance = instance;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        createNotificationChannel();
    }

    public static synchronized MediaControllerFragment newInstance() {
        MediaControllerFragment controllerFragment = new MediaControllerFragment();
        instance = controllerFragment;

        return controllerFragment;
    }

    @Override
    public void onPrepareLayout() {
        content = (Content) getArguments().getSerializable(Constant.Extras.DATA);
        type = (Box.Type) getArguments().getSerializable(Constant.Extras.TYPE);
        //is liked

        instance = this;
        MediaControllerPagerAdapter adapter = new MediaControllerPagerAdapter(getViewContext(), content);
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
                        HomeActivity.getInstance().setupToolBar(null, "https://gentlenobra.com/wp-content/uploads/2021/11/anh-gai-xinh-nobra.jpg", true, null, null);
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


    private void createNotificationChannel() {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel notificationChannel = new NotificationChannel("1", "service",
                    NotificationManager.IMPORTANCE_LOW);
            NotificationManager notificationManager = (NotificationManager) requireActivity().getSystemService(Context.NOTIFICATION_SERVICE);
            if (notificationChannel != null)
                notificationManager.createNotificationChannel(notificationChannel);
        }
    }


    @Override
    public void onPause() {
        super.onPause();
        Log.e("longtv", "onPause: 1" );
        CenterMediaControllerFragment.getInstance().getPlayer().setPlayWhenReady(false);
//        CenterMediaControllerFragment.getInstance().getPlayer().release();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public HomePresenter onCreatePresenter() {
        return null;
    }
}
