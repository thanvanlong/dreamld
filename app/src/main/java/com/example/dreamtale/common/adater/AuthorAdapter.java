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
import com.example.dreamtale.network.dto.Content;
import com.example.dreamtale.utils.CompatibilityUtils;
import com.example.dreamtale.utils.ImageUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AuthorAdapter extends RecyclerView.Adapter<AuthorAdapter.ViewHolder> {

    private List<Content> contentList;
    private int width;
    private Context mContext;

    public AuthorAdapter(List<Content> categoryList, Context context) {
        this.mContext = context;
        this.contentList = categoryList;
    }

    public AuthorAdapter(List<Content> categoryList, int width, Context mContext) {
        this.contentList = categoryList;
        this.width = width;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_author, parent, false);
        int width = CompatibilityUtils.getWidthContentItem(mContext);
        Log.e("longtv", "onCreateViewHolder: " + width );
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(width, ViewGroup.LayoutParams.WRAP_CONTENT);
        view.setLayoutParams(layoutParams);
        return new ViewHolder(view);
    }

    @SuppressLint({"ResourceAsColor", "ClickableViewAccessibility"})
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Content content = contentList.get(position);
        ImageUtils.loadImageOval(mContext, holder.imgAuthor, "https://taimienphi.vn/tmp/cf/aut/anh-gai-xinh-1.jpg");
//        ImageUtils.loadImageOval(mContext, holder.imgAuthor, content.getImg());
        holder.tvNameAuthor.setText("Mr LBee");
//        holder.tvNameAuthor.setText(content.getAuthor());
    }

    @Override
    public int getItemCount() {
        if (contentList != null) {
            return contentList.size();
        }
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.img_author)
        ImageView imgAuthor;
        @BindView(R.id.tv_name_author)
        TextView tvNameAuthor;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
