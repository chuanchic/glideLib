package com.github.glidelib;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.glidelibrary.GlideUtil;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView tv_load_image = findViewById(R.id.tv_load_image);
        tv_load_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImageView iv_image = findViewById(R.id.iv_image);
                String url = "http://cn.bing.com/az/hprichbg/rb/Dongdaemun_ZH-CN10736487148_1920x1080.jpg";
                GlideUtil.loadNetImage(MainActivity.this, iv_image, url, true, 0);
            }
        });

        ImageView iv_gif = findViewById(R.id.iv_gif);
    }
}
