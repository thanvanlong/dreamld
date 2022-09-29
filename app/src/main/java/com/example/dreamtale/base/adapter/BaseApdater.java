package com.example.dreamtale.base.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class BaseApdater<B extends ViewDataBinding, T> extends RecyclerView.Adapter<BaseViewHolder> {
    private Context context;
    private List<T> listObject = new ArrayList<>();
    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (parent != null) {
            context = parent.getContext();
        }

        LayoutInflater layoutInflater = LayoutInflater.from(context);
        B binding = DataBindingUtil.inflate(layoutInflater, getLayoutIdForViewType(viewType), parent, false);
        return new BaseViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        if (listObject != null) {
            return listObject.size();
        }

        return 0;
    }

    protected int getLayoutIdForViewType(int viewType) {
        return viewType;
    }
}
