package org.jasonhww.threaddemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import org.jasonhww.threaddemo.threadLocalDm.ThreadLocalActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "MainActivity";
    private Button threadlocal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initListener();
    }



    private void initView() {
        threadlocal = findViewById(R.id.threadlocal);
    }

    private void initListener() {
        threadlocal.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.threadlocal:
                ThreadLocalActivity.startActivity(this);
                break;
            default:
                break;

        }
    }
}
