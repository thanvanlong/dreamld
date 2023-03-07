package com.example.dreamtale.common.adater;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dreamtale.R;
import com.example.dreamtale.network.dto.Comment;
import com.example.dreamtale.utils.ImageUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.ViewHolder> {
    private List<Comment> comments;
    private Context context;

    public CommentAdapter(List<Comment> comments, Context context) {
        this.comments = comments;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_comment, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Comment comment = comments.get(position);

        if (comment != null) {
            ImageUtils.loadImageOval(context, holder.ivAvtCmt, "https://img.meta.com.vn/Data/image/2022/01/13/anh-dep-thien-nhien-3.jpg");
            holder.ratingBar.setRating(comment.getStar());
            holder.tvNameCmter.setText(comment.getAu().getUsername());
            holder.tvCmt.setText(comment.getComment());
            holder.tvDateCmt.setText(comment.getLocalDateTime());
        }
    }

    @Override
    public int getItemCount() {
        if (comments != null) {
            return comments.size();
        }
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.iv_Avt_Cmt)
        ImageView ivAvtCmt;
        @BindView(R.id.tv_name_cmter)
        TextView tvNameCmter;
        @BindView(R.id.tv_date_cmt)
        TextView tvDateCmt;
        @BindView(R.id.rating)
        RatingBar ratingBar;
        @BindView(R.id.tv_cmt)
        TextView tvCmt;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
