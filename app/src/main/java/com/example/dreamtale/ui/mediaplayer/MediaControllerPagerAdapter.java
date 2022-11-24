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
import com.example.dreamtale.ui.login.LoginFragment;

public class MediaControllerPagerAdapter extends FragmentStateAdapter {
    public MediaControllerPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new CommentFragment();
            case 1:
                CenterMediaControllerFragment centerMediaControllerFragment = CenterMediaControllerFragment.newInstance();
                Bundle bundle = new Bundle();
                bundle.putBoolean(Constant.Extras.CAN_SEARCH, false);
                return centerMediaControllerFragment;

            default:
                return new LoginFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
