package com.example.dreamtale.base;

import android.app.Activity;
import android.os.Bundle;
import android.view.inputmethod.InputMethodManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.dreamtale.R;

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

    public void addFragment(int containerId, Fragment fragment, boolean addToBackStack, String tag ){
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

        fragmentTransaction.add(containerId, fragment, tag);

        if (addToBackStack) {
            fragmentTransaction.addToBackStack(fragment.getClass().getSimpleName());
        }

        fragmentTransaction.commitAllowingStateLoss();

    }

    public void replaceFragment(int containerId, Fragment fragment, String tag) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(containerId, fragment, tag);
        fragmentTransaction.commitAllowingStateLoss();
    }

    public void addFragmentWithTransition(int containerId, Fragment fragment, boolean addToBackStack, String tag) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(
                R.anim.anim_enter_from_right,
                R.anim.anim_exit_to_left,
                R.anim.anim_enter_from_left,
                R.anim.anim_exit_to_right);
        fragmentTransaction.add(containerId, fragment, tag);

        if (addToBackStack) {
            fragmentTransaction.addToBackStack(fragment.getClass().getSimpleName());
        }

        fragmentTransaction.commitAllowingStateLoss();
    }

    public void replaceFragmentWithTransition(int containerId, Fragment fragment, boolean addToBackStack, String tag) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.anim_enter_from_right, R.anim.anim_exit_to_left, R.anim.anim_enter_from_left, R.anim.anim_exit_to_right);
        fragmentTransaction.replace(containerId, fragment, tag);
        if (addToBackStack) {
            fragmentTransaction.addToBackStack(fragment.getClass().getSimpleName());
        }

        fragmentTransaction.commit();
    }

    public void hideSoftKeyboard() {
        InputMethodManager inputMethodManager =
                (InputMethodManager) getSystemService(
                        Activity.INPUT_METHOD_SERVICE);
        if(inputMethodManager.isAcceptingText()){
            inputMethodManager.hideSoftInputFromWindow(
                    getCurrentFocus().getWindowToken(),
                    0
            );
        }
    }
}
