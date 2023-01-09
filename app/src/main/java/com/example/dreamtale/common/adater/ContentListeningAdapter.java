package com.example.dreamtale.common.adater;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dreamtale.R;
import com.example.dreamtale.common.event.ContentItemClick;
import com.example.dreamtale.common.view.ListeningContentBoxView;
import com.example.dreamtale.network.dto.Box;
import com.example.dreamtale.network.dto.Content;
import com.example.dreamtale.utils.CompatibilityUtils;
import com.example.dreamtale.utils.ImageUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ContentListeningAdapter extends RecyclerView.Adapter<ContentListeningAdapter.ViewHolder> {

    private List<Content> contentList;
    private int width;
    private Context mContext;

    public ContentListeningAdapter(List<Content> categoryList, Context context) {
        this.mContext = context;
        this.contentList = categoryList;
    }

    public ContentListeningAdapter(List<Content> categoryList, int width, Context mContext) {
        this.contentList = categoryList;
        this.width = width;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_listening_story, parent, false);
        int width = CompatibilityUtils.getWidthListeningItem(mContext);
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(width, ViewGroup.LayoutParams.WRAP_CONTENT);
        view.setLayoutParams(layoutParams);
        return new ViewHolder(view);
    }

    @SuppressLint({"ResourceAsColor", "ClickableViewAccessibility"})
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Content content = contentList.get(position);
        Log.e("longtv", "onBindViewHolder: 1" + content.getId() + " " + content.getDuration() + " " + content.getProgress() );
        ImageUtils.loadImageCorner(mContext, holder.imgContent, "https://taimienphi.vn/tmp/cf/aut/anh-gai-xinh-1.jpg");
        holder.tvNameEp.setText(content.getContentEp().getName());
        holder.tvNameStory.setText(content.getName());
        holder.seekBar.setMax((int) content.getContentEp().getDuration());
        holder.seekBar.setProgress((int) content.getContentEp().getProgress());

        holder.seekBar.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return true;
            }
        });

        holder.rootView.setOnClickListener(new ContentItemClick(content, Box.Type.AUDIO_LISTENING));
    }

    @Override
    public int getItemCount() {
        if (contentList != null) {
            return contentList.size();
        }
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.img_content)
        ImageView imgContent;
        @BindView(R.id.tv_name_ep)
        TextView tvNameEp;
        @BindView(R.id.tv_name_story)
        TextView tvNameStory;
        @BindView(R.id.seek_bar)
        SeekBar seekBar;
        @BindView(R.id.item_ripple)
        ListeningContentBoxView rootView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
