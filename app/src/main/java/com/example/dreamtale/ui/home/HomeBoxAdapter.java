package com.example.dreamtale.ui.home;

import static com.google.android.exoplayer2.mediacodec.MediaCodecInfo.TAG;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dreamtale.R;
import com.example.dreamtale.common.adater.AuthorAdapter;
import com.example.dreamtale.common.adater.ContentAdapter;
import com.example.dreamtale.common.adater.ContentListeningAdapter;
import com.example.dreamtale.common.view.HorizontalItemDecoration;
import com.example.dreamtale.network.dto.Box;
import com.example.dreamtale.utils.CompatibilityUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeBoxAdapter extends RecyclerView.Adapter<HomeBoxAdapter.ViewHolder> {

    private Context context;
    private List<Box> boxes = new ArrayList<>();

    public HomeBoxAdapter(Context context, List<Box> boxes) {
        this.context = context;
        this.boxes = boxes;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_content, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Box box = boxes.get(position);
        Log.e("anth", "onBindViewHolder: box " + box.getContentList().size() );
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
        HorizontalItemDecoration horizontalItemDecoration = new HorizontalItemDecoration(CompatibilityUtils.getItemSpacing(context));
        holder.tvName.setText(box.getName());
        holder.rcyContent.addItemDecoration(horizontalItemDecoration);
        holder.rcyContent.setLayoutManager(layoutManager);
        switch (box.getType()) {
            case AUDIO_LISTENING:
                holder.rcyContent.setAdapter(new ContentListeningAdapter(box.getContentList(), context));
                break;

            case AUDIO_BOOK:
                int width = CompatibilityUtils.getWidthContentItem(context);
                holder.rcyContent.setAdapter(new ContentAdapter(box.getContentList(), width, context));
                break;
            case AUTHOR:
                int widthAuthor = CompatibilityUtils.getWidthContentItem(context);
                holder.rcyContent.setAdapter(new AuthorAdapter(box.getContentList(), widthAuthor, context));
                break;
        }
    }

    @Override
    public int getItemCount() {

        if(boxes != null) {
            Log.e("longtv", "getItemCount: " + boxes.size());
            return boxes.size();
        }

        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_type_content)
        TextView tvName;
        @BindView(R.id.rcy_content)
        RecyclerView rcyContent;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
        }
    }
}
