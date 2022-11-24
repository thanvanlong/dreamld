package com.example.dreamtale.utils;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.dreamtale.R;

public class ImageUtils {

    public static void loadImageOval(Context context, ImageView imageView, String url) {
        RequestOptions options = new RequestOptions()
                .placeholder(R.color.top_item_package)
                .error(R.color.top_item_package);
        Glide.with(context)
                .load(url)
                .apply(options)
                .circleCrop()
                .into(imageView);
    }

    public static void loadImageCorner(Context context, ImageView imageView, String url) {
        RequestOptions requestOptions = new RequestOptions();
        requestOptions = requestOptions.transforms(new CenterCrop(), new RoundedCorners(16));
        Glide.with(context)
                .load(url)
                .apply(requestOptions)
                .into(imageView);
    }

}
