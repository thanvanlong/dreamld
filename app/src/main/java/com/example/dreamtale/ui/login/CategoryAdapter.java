package com.example.dreamtale.ui.login;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dreamtale.R;
import com.example.dreamtale.network.dto.Category;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {

    private List<Category> categoryList;
    private Context mContext;

    private static CategoryAdapter mInstance;

    public static CategoryAdapter getInstance() {
        return mInstance;
    }

    public CategoryAdapter(List<Category> categoryList, Context context) {
        this.mContext = context;
        this.categoryList = categoryList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_category, parent, false);
        mInstance = this;
        return new ViewHolder(view);
    }

    @SuppressLint({"ResourceAsColor", "ClickableViewAccessibility"})
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Category category = categoryList.get(position);
        if (category.isSelected()) {
            Drawable unwrappedDrawable = AppCompatResources.getDrawable(mContext, R.drawable.bg_category_selected);
            holder.cardView.setBackground(unwrappedDrawable);
            holder.txtCategoryName.setTextColor(ContextCompat.getColor(mContext, R.color.black));
        } else {
            Drawable unwrappedDrawable = AppCompatResources.getDrawable(mContext, R.drawable.bg_category);
            holder.cardView.setBackground(unwrappedDrawable);
            holder.txtCategoryName.setTextColor(ContextCompat.getColor(mContext, R.color.white));
        }

        holder.txtCategoryName.setText(category.getName());

        holder.container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                category.setSelected(!category.isSelected());
                notifyDataSetChanged();
            }
        });

    }

    @Override
    public int getItemCount() {
        if (categoryList != null) {
            return categoryList.size();
        }
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.img_category)
        ImageView imgCategory;
        @BindView(R.id.card_view)
        LinearLayout cardView;
        @BindView(R.id.txt_category_name)
        TextView txtCategoryName;
        @BindView(R.id.container_item)
        RelativeLayout container;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
