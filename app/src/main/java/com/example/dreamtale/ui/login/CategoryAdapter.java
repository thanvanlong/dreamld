package com.example.dreamtale.ui.login;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dreamtale.R;
import com.example.dreamtale.network.dto.Category;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {

    private List<Category> categoryList;
    private Context mContext;
    public CategoryAdapter(List<Category> categoryList, Context context) {
        this.mContext = context;
        this.categoryList = categoryList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_category, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Log.e("longtv", "onBindViewHolder: " + categoryList.size() );
        if (position != 0) {
            int colorId = mContext.getResources().getColor(R.color.dark_blue);
            holder.cardView.setCardBackgroundColor(colorId);
        }
        holder.cardView.setCardElevation(10);
        holder.imgCategory.setImageResource(R.drawable.ic_libs);
        holder.txtCategoryName.setText("Animals");
//        holder.txtCategoryName.setTextColor(R.color.white);
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
        CardView cardView;
        @BindView(R.id.txt_category_name)
        TextView txtCategoryName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
