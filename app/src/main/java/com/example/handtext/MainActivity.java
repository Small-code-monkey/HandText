package com.example.handtext;

import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

/**
 * 线程循环
 * 2019-10-10
 *
 * @author
 */
public class MainActivity extends AppCompatActivity {

    private TextView text;
    private String[] strings = {"a", "b", "c", "d", "e",
            "f", "g", "h", "j"};
    private Button button;
    private boolean b = true;

    private Handler handler;
    private Runnable runnable;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        text = findViewById(R.id.text);

        handler = new Handler();
        handler.post(runnable);
        runnable = new Runnable() {
            @Override
            public void run() {
                text.setText(strings[new Random().nextInt(strings.length)]);
                handler.postDelayed(this, 100);
            }
        };

        button = findViewById(R.id.button);
        button.setOnClickListener(view -> {
            if (b) {
                b = false;
                button.setText("关闭");
                runnable.run();
            } else {
                b = true;
                button.setText("开始");
                handler.removeCallbacks(runnable);
            }
        });


    }
}
