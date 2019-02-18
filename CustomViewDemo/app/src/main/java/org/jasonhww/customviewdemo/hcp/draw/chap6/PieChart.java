package org.jasonhww.customviewdemo.hcp.draw.chap6;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import org.jasonhww.customviewdemo.hcp.draw.utils.Utils;

/**
 * 饼图
 */
public class PieChart extends View {
    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private static final int RADIUS = (int) Utils.dp2px(80);
    private static final int LENGTH = (int) Utils.dp2px(10);
    private static final int PULLED_OUT_INDEX = 0;

    private RectF bounds = new RectF();
    private int[] colors = {Color.parseColor("#2979FF"), Color.parseColor("#C2185B"),
            Color.parseColor("#009688"), Color.parseColor("#FF8F00")};
    //扫过的度数
    private int[] angles = {60, 90, 130, 80};

    public PieChart(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    {
        paint.setStrokeWidth(Utils.dp2px(2));
        paint.setStyle(Paint.Style.FILL);

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        bounds.set((float) getWidth() / 2 - RADIUS, (float) getHeight() / 2 - RADIUS,
                (float) getWidth() / 2 + RADIUS, (float) getHeight() / 2 + RADIUS);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int currentAngles = 0;
        for (int i = 0; i < angles.length; i++) {
            paint.setColor(colors[i]);
            canvas.save();
            if (PULLED_OUT_INDEX == i) {
                float angle = currentAngles + (float) angles[i] / 2;//夹角度数
                canvas.translate(
                        (float) Math.cos(Math.toRadians(angle)) * LENGTH,
                        (float) Math.sin(Math.toRadians(angle) * LENGTH));
            }
            canvas.drawArc(bounds, currentAngles, angles[i], true, paint);
            currentAngles += angles[i];
            canvas.restore();
        }

    }
}
