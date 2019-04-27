package com.github.glidelibrary;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.support.annotation.NonNull;

import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;

import java.security.MessageDigest;

/**
 * 图片灰化处理
 */
public class GlideColorTransformation extends BitmapTransformation {

    @Override
    protected Bitmap transform(@NonNull BitmapPool pool, @NonNull Bitmap source, int outWidth, int outHeight) {
        int width = source.getWidth();
        int height = source.getHeight();

        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);

        Canvas canvas = new Canvas(bitmap);

        Paint paint = new Paint();
        paint.setAntiAlias(true);

        ColorMatrix cm = new ColorMatrix();
        cm.setSaturation(0);

        paint.setColorFilter(new ColorMatrixColorFilter(cm));

        canvas.drawBitmap(source, 0, 0, paint);
        //这里要特别注意，picasso重写的时候，变换前的bitmap要自己释放，而glide不需要，否则也会有错。
        //   source.recycle();
        return bitmap;
    }

    @Override
    public void updateDiskCacheKey(@NonNull MessageDigest messageDigest) {

    }
}
