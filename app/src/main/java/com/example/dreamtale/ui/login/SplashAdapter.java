package com.example.dreamtale.ui.login;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dreamtale.R;
import com.example.dreamtale.dto.ContentSplash;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SplashAdapter extends RecyclerView.Adapter<SplashAdapter.ViewHolder> {
    private List<ContentSplash> list;

    public SplashAdapter(List<ContentSplash> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_splash_activity, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.mBackground.setImageResource(R.drawable.bg_splash);
        holder.mHeaderSplash.setText("Greatest library for sweet dreams");
        holder.mContentSplash.setText("There are over 1000 audio fairy stories from around from the world from our app");
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.img_background)
        ImageView mBackground;
        @BindView(R.id.header_splash)
        TextView mHeaderSplash;
        @BindView(R.id.content_splash)
        TextView mContentSplash;
        private View mRootView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            mRootView = itemView;
        }
    }
}
