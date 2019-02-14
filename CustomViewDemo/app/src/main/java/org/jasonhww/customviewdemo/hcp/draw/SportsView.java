package org.jasonhww.customviewdemo.hcp.draw;

import android.content.Context;
import android.graphics.*;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import org.jasonhww.customviewdemo.hcp.draw.utils.Utils;

public class SportsView extends View {
    private static final String TAG = "SportsView";
    private static final float RING_WIDTH = Utils.dp2px(5);
    private static final float RADIUS = Utils.dp2px(80);
    private static final int CIRCLE_COLOR = Color.parseColor("#90A4AE");
    private static final int HIGHLIGHT_COLOR = Color.parseColor("#FF4081");

    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Rect bounds = new Rect();
    private Paint.FontMetrics fontMetrics;

    public SportsView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    {
        paint.setStrokeWidth(RING_WIDTH);
        paint.setColor(CIRCLE_COLOR);
        paint.setStyle(Paint.Style.STROKE);

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //绘制圆
        canvas.drawCircle(getWidth() / 2, getHeight() / 2, RADIUS, paint);

        //绘制环
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setColor(HIGHLIGHT_COLOR);
        canvas.drawArc(getWidth() / 2 - RADIUS, getHeight() / 2 - RADIUS, getWidth() / 2 + RADIUS, getHeight() / 2 + RADIUS,
                -90, 225, false, paint);

        //绘制文字
        paint.setTextSize(Utils.dp2px(50));
        paint.setStyle(Paint.Style.FILL);//记得设置为fill.

        paint.setTypeface(Typeface.createFromAsset(getContext().getAssets(), "Quicksand-Regular.ttf"));
        //方式1.1:通过获取最小矩形计算出需要偏移的位置,这样可以非常居中.适合静态文字.
       paint.getTextBounds("abab", 0, "abab".length(), bounds);
//        paint.setTextAlign(Paint.Align.CENTER);
//        float offset = (bounds.bottom - bounds.top) / 2;
//        canvas.drawText("abcd", getWidth() / 2, getHeight() / 2 + offset, paint);
        //方式1.2:自行比较实现区别,一看便知.
        paint.setTextAlign(Paint.Align.LEFT);
        float offset = bounds.height() / 2;
        canvas.drawText("abcd", getWidth() / 2 - bounds.width() / 2, getHeight() / 2 + offset, paint);

        //方式二:通过fontMetrics,适合动态设置文字,因为ascent与descent是固定的.
        //paint.setTextAlign(Paint.Align.CENTER);
//        fontMetrics = paint.getFontMetrics();
//        Log.d(TAG, "ascent: " + fontMetrics.ascent);
//        Log.d(TAG, "descent: " + fontMetrics.descent);
//        float offset = (fontMetrics.descent + fontMetrics.ascent) / 2;
//        canvas.drawText("abcd", getWidth() / 2, getHeight() / 2 - offset, paint);

    }
}
