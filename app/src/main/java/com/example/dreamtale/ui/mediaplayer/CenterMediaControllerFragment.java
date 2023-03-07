package com.example.dreamtale.ui.mediaplayer;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.dreamtale.R;
import com.example.dreamtale.base.BaseFragment;
import com.example.dreamtale.common.constant.Constant;
import com.example.dreamtale.common.service.MediaNotificationService;
import com.example.dreamtale.network.dto.Content;
import com.example.dreamtale.network.dto.LogHistory;
import com.example.dreamtale.ui.home.HomeActivity;
import com.example.dreamtale.ui.home.HomePresenter;
import com.example.dreamtale.utils.StringUtils;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.source.DefaultMediaSourceFactory;
import com.google.android.exoplayer2.ui.PlayerView;

import butterknife.BindView;

public class CenterMediaControllerFragment extends BaseFragment<HomePresenter, HomeActivity> implements Player.Listener{

    private static final String TAG = "longtv";
    @BindView(R.id.img_forward)
    LinearLayout btnForward;
    @BindView(R.id.img_rewind)
    LinearLayout btnRewind;
    @BindView(R.id.exo_player)
    PlayerView playerView;
    @BindView(R.id.container_add_time)
    Button ctnAddTime;
    @BindView(R.id.iv_repeat)
    LinearLayout ivRepeatMode;
    @BindView(R.id.btn_pause_play)
    RelativeLayout ivPausePlay;
    @BindView(R.id.img_prev)
    ImageView ivPrev;
    @BindView(R.id.img_next)
    ImageView ivNext;
    @BindView(R.id.container_time_counter)
    LinearLayout ctnCountdown;
    @BindView(R.id.tv_time_count)
    TextView tvTimeCount;
    @BindView(R.id.iv_close_count_down)
    RelativeLayout ivCloseCountDown;
    @BindView(R.id.iv_add_time)
    ImageView ivAddTime;
    @BindView(R.id.iv_sub_time)
    ImageView ivSubTime;

    TextView tvTimeEnd;
    TextView tvTimeStart;
    private int timeRemind = 10;
    private Content content;
    private SeekBar seekBar;
    private int indexMediaItem = 0;
    private CountDownTimer countDownTimer;
    private long currentTimeCount;
    private boolean isSetPositionFirst = false;


    private static CenterMediaControllerFragment instance;

    public static CenterMediaControllerFragment getInstance() {
        return instance;
    }

    public static synchronized CenterMediaControllerFragment newInstance() {
        CenterMediaControllerFragment centerMediaControllerFragment = new CenterMediaControllerFragment();
        instance = centerMediaControllerFragment;

        return centerMediaControllerFragment;

    }

    public Content getContent() {
        return content;
    }

    public void setContent(Content content) {
        this.content = content;
    }

    private ExoPlayer player;

    public ExoPlayer getPlayer() {
        return player;
    }

    public void setPlayer(ExoPlayer player) {
        this.player = player;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_center_controller;
    }

    @Override
    public void onPrepareLayout() {
        Bundle data = getArguments();
        seekBar = MediaControllerFragment.getInstance().getSeekBar();
        content = (Content) data.getSerializable(Constant.Extras.DATA);
        tvTimeEnd = MediaControllerFragment.getInstance().getTvTimeEnd();
        tvTimeStart = MediaControllerFragment.getInstance().getTvTimeStart();
//        if (player != null) {
//            player.setPlayWhenReady(false);
//            player.release();
//        }
        Log.e(TAG, "onPrepareLayout: " + content.getId() );
        if (content != null) {
            intiExoplayer(content.getDataStream() != null ? content.getDataStream().getUrl() : content.getContentEp().getDataStream().getUrl());
        }
        if (player != null) {
            btnForward.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    player.seekTo(player.getCurrentPosition() + 15000);
                }
            });

            ivSubTime.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    countDownTimer.cancel();
                    countDownTimer = null;
                    long time = currentTimeCount - 60000;
                    if (time <= 0) {
                        time = 0;
                    }

                    initCountDown(time / 1000);
                }
            });

            ivAddTime.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.e(TAG, "onClick: add time" );
                    countDownTimer.cancel();
                    countDownTimer = null;
                    long time = currentTimeCount + 60000;
                    initCountDown(time / 1000);
                }
            });

            ivCloseCountDown.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    countDownTimer.cancel();
                    countDownTimer = null;
                    ctnCountdown.setVisibility(View.GONE);
                    ctnAddTime.setVisibility(View.VISIBLE);
                }
            });

            btnRewind.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (player.getCurrentPosition() <= 15000 ) {
                        player.seekTo(0);
                    } else {
                        player.seekTo(player.getCurrentPosition() - 15000);
                    }
                }
            });

            ivRepeatMode.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ImageView imageView = (ImageView) ivRepeatMode.getChildAt(0);
                    if (player.getRepeatMode() == Player.REPEAT_MODE_OFF) {
                        player.setRepeatMode(Player.REPEAT_MODE_ONE);
                        imageView.setImageResource(R.drawable.ic_repeat_active);
                    } else {
                        player.setRepeatMode(Player.REPEAT_MODE_OFF);
                        imageView.setImageResource(R.drawable.ic_repeat);
                    }
                }
            });

            ivPausePlay.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    player.setPlayWhenReady(!player.getPlayWhenReady());
                    updatePlayer();
                    Log.e(TAG, "onClick: play pause" );
                }
            });
        }

        ivNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nextContent();
            }
        });

        ivPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                prevContent();
            }
        });

        Handler handler = new Handler();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                FragmentManager fragmentManager = getViewContext().getSupportFragmentManager();
                Fragment fragment = fragmentManager.findFragmentById(R.id.frg_common_content);
                if (fragment instanceof  DetailFragment) {
                    return;
                }
                sendLog();
                if (player.getPlaybackState() == Player.STATE_ENDED) {
                    handler.removeCallbacks(this);
                    return;
                }
                handler.postDelayed(this::run, 10 * 1000);
            }
        };

        handler.postDelayed(runnable, 10 * 1000);



        ctnAddTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ctnCountdown.getVisibility() == View.GONE) {
                    ctnCountdown.setVisibility(View.VISIBLE);
                    ctnAddTime.setVisibility(View.GONE);
                    initCountDown(timeRemind);
                }
            }
        });

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                player.seekTo(seekBar.getProgress());
            }
        });

    }

    @Override
    public void onResume() {
        super.onResume();
        Log.e(TAG, "onResume: " + player );
        HomeActivity.getInstance().setupToolBar(null, "https://gentlenobra.com/wp-content/uploads/2021/11/anh-gai-xinh-nobra.jpg", true, null, null);
    }

    @Override
    public void onPause() {
        super.onPause();

    }

    @Override
    public void onStop() {
        super.onStop();
    }

    private void intiExoplayer(String url ) {
        player = new ExoPlayer.Builder(getViewContext()).build();
        playerView.setPlayer(player);
        MediaItem firstItem = MediaItem.fromUri(Uri.parse("https://cph-p2p-msl.akamaized.net/hls/live/2000341/test/master.m3u8"));
        player.addMediaItem(firstItem);
        player.prepare();
        player.addListener(this);
        // TODO history listening
        player.setPlayWhenReady(true);

        if (HomeActivity.getInstance() != null) {
            LogHistory logHistory = new LogHistory();
            logHistory.setProgress(0);
            logHistory.setAudioBookEpId(content.getContentEp() == null ? (int) content.getId() : (int) content.getContentEp().getAudioBookEp());
            logHistory.setAudioBookId(content.getContentEp () == null ? (int) DetailFragment.getInstance().getContent().getId() : (int) content.getAudioBook());
            HomeActivity.getInstance().logHistory(logHistory);
        }

    }

    private void sendLog() {
        if (HomeActivity.getInstance() != null && player != null) {
            LogHistory logHistory = new LogHistory();
            logHistory.setProgress(player.getCurrentPosition());
            logHistory.setAudioBookEpId(content.getContentEp() == null ? (int) content.getId() : (int) content.getContentEp().getAudioBookEp());
            logHistory.setAudioBookId(content.getContentEp () == null ? (int) DetailFragment.getInstance().getContent().getId() : (int) content.getAudioBook());
            logHistory.setDuration(player.getDuration());
            HomeActivity.getInstance().logHistory(logHistory);
        }

    }

    @Override
    public void onPlayerStateChanged(boolean playWhenReady, int playbackState) {
        Player.Listener.super.onPlayerStateChanged(playWhenReady, playbackState);
        Log.e(TAG, "onPlayerStateChanged: " + playbackState );
        if (playWhenReady) {
            initSeekBar();
            ImageView ivPp = (ImageView) ivPausePlay.getChildAt(0);
            if (playbackState == Player.STATE_READY) {
                ivPp.setImageResource(R.drawable.ic_play);
            }
        }

        switch (playbackState) {
            //TODO handle state audio
            case Player.STATE_ENDED:
                ImageView imageView = (ImageView) ivPausePlay.getChildAt(0);
                imageView.setImageResource(R.drawable.ic_pause);
            case Player.STATE_READY:
                if (!isSetPositionFirst && content.getContentEp() != null) {
                    player.seekTo(content.getContentEp().getProgress());
                    isSetPositionFirst = true;
                }
                updatePlayer();
                tvTimeStart.setText(StringUtils.covertSecondToHMS(player.getCurrentPosition() / 1000));
                tvTimeEnd.setText(StringUtils.covertSecondToHMS(player.getDuration() / 1000));
        }
    }

    private void initSeekBar() {
        seekBar.setMax((int) player.getDuration());
        seekBar.setProgress((int) player.getCurrentPosition());

        Handler handler = new Handler();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                if (player != null) {
                    seekBar.setProgress((int) player.getCurrentPosition());
                    tvTimeStart.setText(StringUtils.covertSecondToHMS(player.getCurrentPosition() / 1000));
                    handler.postDelayed(this, 1000);
                }
            }
        };

        handler.postDelayed(runnable, 1000);
    }

    public void nextContent() {
        int index = DetailFragment.getInstance().getContentList().indexOf(content);
        if (index == DetailFragment.getInstance().getContentList().size() - 2) {
            ivNext.setVisibility(View.INVISIBLE);
        }

        content = DetailFragment.getInstance().getContentList().get(index + 1);
        player.release();
        intiExoplayer(content.getDataStream().getUrl());
    }

    public void prevContent() {
        int index = DetailFragment.getInstance().getContentList().indexOf(content);
        if (index == 1) {
            ivNext.setVisibility(View.INVISIBLE);
        }

        content = DetailFragment.getInstance().getContentList().get(index - 1);
        player.release();
        intiExoplayer(content.getDataStream().getUrl());
    }


    private void initCountDown(long timeRemind) {
        if (countDownTimer == null) {
            countDownTimer = new CountDownTimer(timeRemind * 1000, 1000) {
                @Override
                public void onTick(long l) {
                    Log.e(TAG, "onTick: " + l );
                    currentTimeCount = l;
                    tvTimeCount.setText(StringUtils.covertSecondToHMS(l / 1000));
                }

                @Override
                public void onFinish() {
                    player.setPlayWhenReady(false);
                    MediaNotificationService.getInstance().setPlaying(false);
                    MediaNotificationService.getInstance().sendNotification(MediaNotificationService.getInstance().getContentObj(),
                            MediaNotificationService.getInstance().isEndSeries(),
                            MediaNotificationService.getInstance().isStartSeries(),
                            MediaNotificationService.getInstance().getPosition(),
                            MediaNotificationService.getInstance().getDuration());
                    updatePlayer();
                }
            };

            countDownTimer.start();
        }
    }

    public void updatePlayer() {
        ImageView imageView = (ImageView) ivPausePlay.getChildAt(0);
        if (player.getPlayWhenReady()) {
            imageView.setImageResource(R.drawable.ic_play);
        } else {
            imageView.setImageResource(R.drawable.ic_pause);
        }
    }

    @Override
    public HomePresenter onCreatePresenter() {
        return null;
    }
}
