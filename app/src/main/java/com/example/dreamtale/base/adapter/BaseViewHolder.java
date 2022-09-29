package com.example.dreamtale.base.adapter;

import android.view.View;

import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

public class BaseViewHolder<B extends ViewDataBinding> extends RecyclerView.ViewHolder {
    private B mBinding;
    public BaseViewHolder(B binding) {
        super(binding.getRoot());
        mBinding = binding;
    }

    public BaseViewHolder(View view) {
        super(view);
        mBinding = DataBindingUtil.bind(view);
    }
    public B getBinding() {
        return mBinding;
    }
}

