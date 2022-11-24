package com.example.dreamtale.common.adater;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dreamtale.R;
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
        ImageUtils.loadImageCorner(mContext, holder.imgContent, content.getImg());

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

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
