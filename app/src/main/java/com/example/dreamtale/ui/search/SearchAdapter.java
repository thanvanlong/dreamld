package com.example.dreamtale.ui.search;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dreamtale.R;
import com.example.dreamtale.common.event.ContentItemClick;
import com.example.dreamtale.network.dto.Box;
import com.example.dreamtale.network.dto.Content;
import com.example.dreamtale.ui.login.CategoryAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ViewHolder>{

    private List<Content> contentList;
    private Context mContext;
    private int item;
    public SearchAdapter(Context context, List<Content> contentList, int item) {
        this.contentList = contentList;
        this.mContext = context;
        this.item = item;
    }

    @NonNull
    @Override
    public SearchAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchAdapter.ViewHolder holder, int position) {
        Content content = contentList.get(position);
        Log.e("longtv", "onBindViewHolder: asdasdsad" + content.getName() );
        holder.tvNameContent.setText(content.getName());

//        if (holder.ivClose != null) {
//            holder.ivClose.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    contentList.remove(content);
//                    notifyDataSetChanged();
//                }
//            });
//        }
        holder.rootView.setOnClickListener(new ContentItemClick(content, Box.Type.AUDIO_BOOK));
//        if (content.getDataStream() == null) {
//            holder.tvNameContent.setOnClickListener(new ContentItemClick(content, Box.Type.AUDIO_BOOK));
//        } else {
//            holder.tvNameContent.setOnClickListener(new ContentItemClick(content, Box.Type.AUDIO_DETAIL));
//        }
    }

    @Override
    public int getItemCount() {
        if (contentList != null) {
            return contentList.size();
        }
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_name_content)
        TextView tvNameContent;
        @BindView(R.id.layout_container)
        RelativeLayout rootView;
//        @BindView(R.id.iv_close)
//        ImageView ivClose;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
