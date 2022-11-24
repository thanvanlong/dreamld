package com.example.dreamtale.ui.mediaplayer;

import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.dreamtale.R;
import com.example.dreamtale.base.BaseFragment;
import com.example.dreamtale.network.dto.Content;
import com.example.dreamtale.ui.home.HomeActivity;
import com.example.dreamtale.ui.home.HomePresenter;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.ui.PlayerView;

import butterknife.BindView;

public class CenterMediaControllerFragment extends BaseFragment<HomePresenter, HomeActivity> implements Player.Listener{

    private static final String TAG = "longtv";
    @BindView(R.id.img_forward)
    LinearLayout layout;
    @BindView(R.id.exo_player)
    PlayerView playerView;

    @BindView(R.id.btn_pause_play)
    ImageView ivPausePlay;

    private Content content;


    private static CenterMediaControllerFragment instance;

    public static CenterMediaControllerFragment getInstance() {
        return instance;
    }

    public static synchronized CenterMediaControllerFragment newInstance() {
        CenterMediaControllerFragment centerMediaControllerFragment = new CenterMediaControllerFragment();
        instance = centerMediaControllerFragment;

        return centerMediaControllerFragment;

    }

    private ExoPlayer player;
    @Override
    public int getLayoutId() {
        return R.layout.fragment_center_controller;
    }

    @Override
    public void onPrepareLayout() {
        Bundle data = getArguments();
        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        //TODO set data for layout such as name, chapter, liked, current position, duration

        //init exoplayer

    }

    @Override
    public void onResume() {
        super.onResume();

        //TODO handle audio if is running
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    private void intiExoplayer(String url ) {
        player = new ExoPlayer.Builder(getViewContext()).build();
        playerView.setPlayer(player);
        MediaItem firstItem = MediaItem.fromUri(Uri.parse("https://cdn.discordapp.com/attachments/775740994595323954/775741533789224960/Alan_Walker_-_Sing_Me_To_SleepMP3_160K.mp3"));
        player.addMediaItem(firstItem);
        player.prepare();
        player.addListener(this);
        // TODO history listening
        player.play();
    }

    @Override
    public void onPlayerStateChanged(boolean playWhenReady, int playbackState) {
        Player.Listener.super.onPlayerStateChanged(playWhenReady, playbackState);
        Log.e(TAG, "onPlayerStateChanged: " + playbackState );
        if (playWhenReady) {
            initSeekBar();
        }

        switch (playbackState) {
            //TODO handle state audio
        }
    }

    private void initSeekBar() {
//        seekBar.setMax((int) player.getDuration());
//        seekBar.setProgress((int) player.getCurrentPosition());
//
//        Handler handler = new Handler();
//        Runnable runnable = new Runnable() {
//            @Override
//            public void run() {
//                seekBar.setProgress((int) player.getCurrentPosition());
//                handler.postDelayed(this, 1000);
//            }
//        };
//
//        handler.postDelayed(runnable, 1000);
    }



    @Override
    public HomePresenter onCreatePresenter() {
        return null;
    }
}
