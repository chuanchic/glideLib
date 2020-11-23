package com.github.glidelibrary;

import android.content.Context;
import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.Registry;
import com.bumptech.glide.module.GlideModule;

public class MyGlideModule implements GlideModule {

    @Override
    public void applyOptions(@NonNull Context context, @NonNull GlideBuilder builder) {
        //4.0以上的版本，默认解码方式为：ARGB_8888，修改为：PREFER_RGB_565
        //builder.setDefaultRequestOptions(new RequestOptions().format(DecodeFormat.PREFER_RGB_565));

    }

    @Override
    public void registerComponents(@NonNull Context context, @NonNull Glide glide, @NonNull Registry registry) {

    }

}
