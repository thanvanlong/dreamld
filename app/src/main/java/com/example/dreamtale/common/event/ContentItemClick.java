package com.example.dreamtale.common.event;

import android.os.Bundle;
import android.view.View;

import com.example.dreamtale.R;
import com.example.dreamtale.common.constant.Constant;
import com.example.dreamtale.network.dto.Box;
import com.example.dreamtale.network.dto.Content;
import com.example.dreamtale.ui.home.HomeActivity;
import com.example.dreamtale.ui.mediaplayer.DetailFragment;
import com.example.dreamtale.ui.mediaplayer.CenterMediaControllerFragment;

public class ContentItemClick implements View.OnClickListener {

    private Content content;
    private Box.Type type;

    public ContentItemClick(Content content, Box.Type type) {
        this.content = content;
        this.type = type;
    }

    @Override
    public void onClick(View view) {
        switch (type) {
            case AUDIO_BOOK:
                //TODO go to audio detail
                gotoAudio();
                break;
            case AUDIO_DETAIL:
                //TODO go to play audio
                gotoAudioDetail();
                break;
        }
    }


    public void gotoAudio() {
        Bundle bundle = new Bundle();
        bundle.putSerializable(Constant.Extras.DATA, content);
        bundle.putBoolean(Constant.Extras.CAN_SEARCH, false);
        bundle.putBoolean(Constant.Extras.TOOL_TITLE, true);
        bundle.putBoolean(Constant.Extras.NAVIGATION, false);

        DetailFragment detailFragment = DetailFragment.newInstance();
        detailFragment.setArguments(bundle);

        HomeActivity.getInstance().addFragment(R.id.frg_common_content, detailFragment, bundle, true, DetailFragment.class.getSimpleName() );
    }


    public void gotoAudioDetail() {
        Bundle bundle = new Bundle();
        bundle.putSerializable(Constant.Extras.DATA, content);
        bundle.putBoolean(Constant.Extras.CAN_SEARCH, false);
        bundle.putBoolean(Constant.Extras.TOOL_TITLE, true);
        bundle.putBoolean(Constant.Extras.NAVIGATION, false);

        CenterMediaControllerFragment controllerFragment = CenterMediaControllerFragment.newInstance();
        controllerFragment.setArguments(bundle);

        HomeActivity.getInstance().addFragment(R.id.frg_common_content, controllerFragment, bundle, true, DetailFragment.class.getSimpleName() );
    }

}
