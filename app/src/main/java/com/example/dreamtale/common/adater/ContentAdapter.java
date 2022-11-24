package com.example.dreamtale.common.adater;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dreamtale.R;
import com.example.dreamtale.common.event.ContentItemClick;
import com.example.dreamtale.common.view.ContentBoxView;
import com.example.dreamtale.network.dto.Box;
import com.example.dreamtale.network.dto.Category;
import com.example.dreamtale.network.dto.Content;
import com.example.dreamtale.utils.CompatibilityUtils;
import com.example.dreamtale.utils.ImageUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ContentAdapter extends RecyclerView.Adapter<ContentAdapter.ViewHolder> {

    private List<Content> contentList;
    private int width;
    private Context mContext;

    public ContentAdapter(List<Content> categoryList, Context context) {
        this.mContext = context;
        this.contentList = categoryList;
    }

    public ContentAdapter(List<Content> categoryList, int width, Context mContext) {
        this.contentList = categoryList;
        this.width = width;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_content_box, parent, false);
        if (width > 0) {
            ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(width, ViewGroup.LayoutParams.WRAP_CONTENT);
            view.setLayoutParams(layoutParams);
        }
        return new ViewHolder(view);
    }

    @SuppressLint({"ResourceAsColor", "ClickableViewAccessibility"})
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Content content = contentList.get(position);

        ImageUtils.loadImageCorner(mContext, holder.imgContent, contentList.get(position).getCoverImg());
        holder.tvNameContent.setText(content.getName());
        holder.tvNameContent.setSelected(true);

        if (content.getDataStream() != null) {
            holder.contentBoxView.setOnClickListener(new ContentItemClick(content, Box.Type.AUDIO_DETAIL));
        } else {
            holder.contentBoxView.setOnClickListener(new ContentItemClick(content, Box.Type.AUDIO_BOOK));
        }

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
        @BindView(R.id.tv_name_content)
        TextView tvNameContent;
        @BindView(R.id.container_ripple_item)
        ContentBoxView contentBoxView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
