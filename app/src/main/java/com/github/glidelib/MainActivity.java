package com.github.glidelib;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.github.glidelibrary.GlideUtil;
import com.sunfusheng.GlideImageView;
import com.sunfusheng.progress.CircleProgressView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView tv_load_image = findViewById(R.id.tv_load_image);
        tv_load_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String url = "https://raw.githubusercontent.com/sfsheng0322/GlideImageView/master/resources/girl.jpg";
                //GlideUtil.loadNetImage(MainActivity.this, iv_image, url, true, 0);

                GlideImageView iv_image = findViewById(R.id.iv_image);
                CircleProgressView progressView = findViewById(R.id.progressView);
                GlideUtil.loadWithProgress(iv_image, progressView, url, false);
            }
        });

    }
}
