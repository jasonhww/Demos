package org.jasonhww.customviewdemo.scroolmethod;

import android.app.Activity;
import android.os.Bundle;
import android.util.TypedValue;
import org.jasonhww.customviewdemo.R;

public class ScrollActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroll);
        //将普通数值转换成对应数据类型的数值
        TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,666,getResources().getDisplayMetrics());
        TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,666,getResources().getDisplayMetrics());
        TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PX,666,getResources().getDisplayMetrics());
    }


}
