package com.example.dreamtale.ui.mediaplayer;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.dreamtale.common.constant.Constant;
import com.example.dreamtale.common.fragment.CommentFragment;
import com.example.dreamtale.common.fragment.ListContentFragment;
import com.example.dreamtale.network.dto.Content;
import com.example.dreamtale.ui.login.LoginFragment;

public class MediaControllerPagerAdapter extends FragmentStateAdapter {
    private Content content;
    public MediaControllerPagerAdapter(@NonNull FragmentActivity fragmentActivity, Content content) {
        super(fragmentActivity);
        this.content = content;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                CommentFragment commentFragment = CommentFragment.newInstance();
                return commentFragment;
            case 1:
                CenterMediaControllerFragment centerMediaControllerFragment = CenterMediaControllerFragment.newInstance();
                Bundle bundle = new Bundle();
                bundle.putBoolean(Constant.Extras.CAN_SEARCH, false);
                bundle.putSerializable(Constant.Extras.DATA, content);
                centerMediaControllerFragment.setArguments(bundle);
                return centerMediaControllerFragment;

            default:
                ListContentFragment listContentFragment = ListContentFragment.newInstance();
                Bundle bd = new Bundle();
                bd.putBoolean(Constant.Extras.CAN_SEARCH, false);
                bd.putSerializable(Constant.Extras.DATA, content);
                listContentFragment.setArguments(bd);
                return listContentFragment;
        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
