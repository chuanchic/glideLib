package com.github.glidelibrary;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.FutureTarget;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.Target;
import com.sunfusheng.GlideImageView;
import com.sunfusheng.progress.CircleProgressView;

import java.io.File;

/**
 * 图片加载框架
 * 参考：https://blog.csdn.net/guolin_blog/article/details/78582548
 */
public class GlideUtil {

    /**
     * 请求配置
     */
    private static RequestOptions createRequestOptions(boolean isGif, boolean isLocal, boolean centerCrop, int width, int height, int defaultDrawable){
        RequestOptions options = new RequestOptions();

        //设置缓存策略，默认：DiskCacheStrategy.AUTOMATIC
        if(isGif){//gif图片
            options.diskCacheStrategy(DiskCacheStrategy.DATA);//只缓存原始图片，不然加载会很慢
        }else if(isLocal){//本地图片
            options.diskCacheStrategy(DiskCacheStrategy.RESOURCE);//只缓存转换过后的图片
        }else{//网络图片
            options.diskCacheStrategy(DiskCacheStrategy.ALL);//既缓存原始图片，也缓存转换过后的图片
        }
        //设置显示方式
        if(centerCrop){
            options.centerCrop();
        }else{
            options.fitCenter();
        }
        //设置裁剪大小
        if(width > 0 && height > 0){
            //加载图片原始尺寸：用 Target.SIZE_ORIGINAL
            options.override(width, height);
        }
        //设置占位符
        if(defaultDrawable > 0){
            options.placeholder(defaultDrawable);
        }
        //阻止动画
        if(isGif){
        }else{
            options.dontAnimate();
        }

        return options;
    }

    /**
     * 加载监听
     */
    private static RequestListener createRequestListener(){
        return new RequestListener<Drawable>() {
            @Override
            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {

                return false;//不处理这个事件，继续向下传递
            }

            @Override
            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {

                return false;//不处理这个事件，继续向下传递
            }
        };
    }

    /**
     * 加载并显示图片
     */
    public static void load(Context context, Fragment fragment, ImageView iv, String url, int drawable, boolean isGif, boolean isLocal, boolean centerCrop, int width, int height, int defaultDrawable){
        if(iv == null){
            return;
        }

        RequestManager requestManager;
        if(context == null){
            if(fragment == null){
                return;
            }
            requestManager = Glide.with(fragment);
        }else{
            requestManager = Glide.with(context);
        }

        if(isGif){
            /**
             * 不需要指定图片格式，Glide内部会自动判断图片格式
             * asBitmap：静态图片的加载，如果图片为gif格式，只会加载第一帧
             * asGif：gif图片的加载
             * asFile：文件格式图片的加载
             * asDrawable：rawable格式图片的加载
             */
            requestManager.asGif();
        }

        RequestBuilder<Drawable> builder;
        if(drawable > 0){
            builder = requestManager.load(drawable);
        }else{
            if(TextUtils.isEmpty(url)){
                return;
            }
            builder = requestManager.load(url);
        }

        builder.apply(createRequestOptions(isGif, isLocal, centerCrop, width, height, defaultDrawable))
                .into(iv);
    }

    public static void loadNetImage(Context context, ImageView iv, String url, boolean centerCrop, int defaultDrawable) {
        load(context, null, iv, url, 0, false, false, centerCrop, 0, 0, defaultDrawable);
    }

    public static void loadNetImage(Fragment fragment, ImageView iv, String url, boolean centerCrop, int defaultDrawable) {
        load(null, fragment, iv, url, 0, false, false, centerCrop, 0, 0, defaultDrawable);
    }


    public static void loadNetGif(Context context, ImageView iv, String url, boolean centerCrop, int defaultDrawable) {
        load(context, null, iv, url, 0, true, false, centerCrop, 0, 0, defaultDrawable);
    }

    public static void loadNetGif(Fragment fragment, ImageView iv, String url, boolean centerCrop, int defaultDrawable) {
        load(null, fragment, iv, url, 0, true, false, centerCrop, 0, 0, defaultDrawable);
    }

    public static void loadLocalImage(Context context, ImageView iv, String url, boolean centerCrop, int defaultDrawable) {
        load(context, null, iv, url, 0, false, true, centerCrop, 0, 0, defaultDrawable);
    }

    public static void loadLocalImage(Fragment fragment, ImageView iv, String url, boolean centerCrop, int defaultDrawable) {
        load(null, fragment, iv, url, 0, false, true, centerCrop, 0, 0, defaultDrawable);
    }

    public static void loadLocalDrawable(Context context, ImageView iv, int drawable, boolean centerCrop, int defaultDrawable) {
        load(context, null, iv, null, drawable, false, true, centerCrop, 0, 0, defaultDrawable);
    }

    public static void loadLocalDrawable(Fragment fragment, ImageView iv, int drawable, boolean centerCrop, int defaultDrawable) {
        load(null, fragment, iv, null, drawable, false, true, centerCrop, 0, 0, defaultDrawable);
    }

    public static void loadLocalGif(Context context, ImageView iv, String url, boolean centerCrop, int defaultDrawable) {
        load(context, null, iv, url, 0, true, true, centerCrop, 0, 0, defaultDrawable);
    }

    public static void loadLocalGif(Fragment fragment, ImageView iv, String url, boolean centerCrop, int defaultDrawable) {
        load(null, fragment, iv, url, 0, true, true, centerCrop, 0, 0, defaultDrawable);
    }

    public static void loadLocalGif(Context context, ImageView iv, int drawable, boolean centerCrop, int defaultDrawable) {
        load(context, null, iv, null, drawable, true, true, centerCrop, 0, 0, defaultDrawable);
    }

    public static void loadLocalGif(Fragment fragment, ImageView iv, int drawable, boolean centerCrop, int defaultDrawable) {
        load(null, fragment, iv, null, drawable, true, true, centerCrop, 0, 0, defaultDrawable);
    }

    /**
     * 下载Bitmap
     * new SimpleTarget()：下载原始尺寸
     * new SimpleTarget(100, 100)：下载指定尺寸
     */
    public static void downloadBitmap(Context context, String url, SimpleTarget<Bitmap> simpleTarget){
        Glide.with(context).asBitmap().load(url).into(simpleTarget);
    }

    /**
     * 下载Drawable
     * new SimpleTarget()：下载原始尺寸
     * new SimpleTarget(100, 100)：下载指定尺寸
     */
    public static void downloadDrawable(Context context, String url, SimpleTarget<Drawable> simpleTarget){
        Glide.with(context).asDrawable().load(url).into(simpleTarget);
    }

    /**
     * 下载图片文件
     * 该方法需要放在子线程调用，因为下载图片是耗时的操作
     */
    public static File downloadImageFile(Context context, String url, int width, int height){
        File imageFile = null;
        try {
            RequestBuilder<File> requestBuilder = Glide.with(context.getApplicationContext()).asFile().load(url);

            FutureTarget<File> target;
            if(width > 0 && height > 0){//下载指定尺寸
                target = requestBuilder.submit(width, height);
            }else{//下载原始尺寸
                target = requestBuilder.submit();
            }

            imageFile = target.get();//会阻塞当前线程，直到图片下载完成
        } catch (Exception e) {
            e.printStackTrace();
        }
        return imageFile;
    }

    /**
     * 加载图片（同时显示加载进度，一般用来显示大图）
     */
    public static void loadWithProgress(GlideImageView iv, CircleProgressView pv, String url, boolean centerCrop){
        if(iv == null || pv == null || TextUtils.isEmpty(url)){
            return;
        }
        if(centerCrop){
            iv.centerCrop();
        }else{
            iv.fitCenter();
        }
        iv.error(R.mipmap.image_load_err)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .load(url, R.color.placeholder, (isComplete, percentage, bytesRead, totalBytes) -> {
            if (isComplete) {//加载完成
                pv.setVisibility(View.GONE);
            } else {//加载中
                pv.setVisibility(View.VISIBLE);
                pv.setProgress(percentage);
            }
        });
    }

}
