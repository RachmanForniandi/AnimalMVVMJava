package com.example.animalmvvmjavaapp.utility;

import android.content.Context;
import android.widget.ImageView;

import androidx.swiperefreshlayout.widget.CircularProgressDrawable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.animalmvvmjavaapp.R;

public class ImageLoader {

    public static void loadImage(ImageView img, String url, CircularProgressDrawable circularProgressDrawable){
        RequestOptions requestOptions = new RequestOptions()
                .placeholder(circularProgressDrawable)
                .error(R.drawable.placeholder);

        Glide.with(img.getContext())
                .setDefaultRequestOptions(requestOptions)
                .load(url)
                .into(img);
    }

    public static CircularProgressDrawable getProgressDrawable(Context context){
        CircularProgressDrawable cpd = new CircularProgressDrawable(context);
        cpd.setStrokeWidth(10f);
        cpd.setCenterRadius(50f);
        cpd.start();
        return cpd;
    }
}
