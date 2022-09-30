package com.example.dreamtale.base;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseActivity<T extends BasePresenter> extends AppCompatActivity implements BaseView {

    private T mPresenter;
    private Unbinder mUnbinder;
    private boolean isBinding = false;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (!isBinding) {
            setContentView(getLayoutId());
            mUnbinder = ButterKnife.bind(this);
            mPresenter = (T) onCreatePresenter();
            onPrepareLayout();
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("PID", android.os.Process.myPid() + "");
        outState.putSerializable("android:support:fragments", null);
    }

    @Override
    public T getPresenter() {
        return mPresenter;
    }

    public abstract int getLayoutId();

    public void addFragment(int containerId, Fragment fragment, Bundle bundle, boolean addToBackStack, String tag) {
        if (bundle != null) {
            fragment.setArguments(bundle);
        }

        addFragment(containerId, fragment, addToBackStack, tag);
    }

    public void addFragment(int containerId ,Fragment fragment, boolean addToBackStack, String tag ){
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

        fragmentTransaction.add(containerId, fragment, tag);

        if (addToBackStack) {
            fragmentTransaction.addToBackStack(fragment.getClass().getSimpleName());
        }

        fragmentTransaction.commitAllowingStateLoss();

    }

}
