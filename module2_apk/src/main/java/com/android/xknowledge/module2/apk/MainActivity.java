package com.android.xknowledge.module2.apk;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.android.xknowledge.annotation.BindPath;
import com.android.xknowledge.router.ARouter;

@BindPath("module2/MainActivity")
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        findViewById(R.id.module2_button_module1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ARouter.getInstance().jumapActivity("module1/MainActivity", null);
            }
        });
    }
}
