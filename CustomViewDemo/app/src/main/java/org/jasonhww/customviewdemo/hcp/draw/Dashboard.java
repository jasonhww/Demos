package org.jasonhww.customviewdemo.hcp.draw;

import android.content.Context;
import android.graphics.*;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import org.jasonhww.customviewdemo.hcp.draw.utils.Utils;

public class Dashboard extends View {
    private static final String TAG = "Dashboard";

    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private static final float LENGTH = Utils.dp2px(50);
    //弧的半径
    private static final float RADIUS = Utils.dp2px(80);
    //基础度数
    public static final int ANGLE = 120;
    private Path dashPath = new Path();
    private PathDashPathEffect pathDashPathEffect;

    public Dashboard(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    {
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(Utils.dp2px(2));

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        //刻度利用path画长方形
        dashPath.addRect(0, 0, Utils.dp2px(1), Utils.dp2px(10), Path.Direction.CW);
        Path arc = new Path();
        Log.d(TAG, "instance initializer: "+getWidth());
        arc.addArc((float) getWidth() / 2 - RADIUS, (float) getHeight() / 2 - RADIUS,
                (float) getWidth() / 2 + RADIUS, (float) getHeight() / 2 + RADIUS,
                90 + (float) ANGLE / 2, 360 - ANGLE);
        //利用PathMeasure,测量出弧path的长度
        PathMeasure pathMeasure = new PathMeasure(arc, false);
        //计算出每个刻度间隔.公式:弧长/刻度个数,
        // 由于结尾多一个刻度线也需要计算进去,所以(弧长 - 一个刻度线的宽度)/刻度个数
        float advance = (pathMeasure.getLength() - Utils.dp2px(1)) / 20;
        pathDashPathEffect = new PathDashPathEffect(dashPath, advance, 0, PathDashPathEffect.Style.ROTATE);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //画弧
        canvas.drawArc((float) getWidth() / 2 - RADIUS, (float) getHeight() / 2 - RADIUS,
                (float) getWidth() / 2 + RADIUS, (float) getHeight() / 2 + RADIUS,
                90 + (float) ANGLE / 2, 360 - ANGLE, false, paint);

        //画刻度
        paint.setPathEffect(pathDashPathEffect);
        canvas.drawArc((float) getWidth() / 2 - RADIUS, (float) getHeight() / 2 - RADIUS,
                (float) getWidth() / 2 + RADIUS, (float) getHeight() / 2 + RADIUS,
                90 + (float) ANGLE / 2, 360 - ANGLE, false, paint);

        //画指针
        paint.setPathEffect(null);
        //起点为圆心,终点则是相对圆心的位移位置.
        canvas.drawLine((float) getWidth() / 2, (float) getHeight() / 2,
                (float) getWidth() / 2 + (float) Math.cos(Math.toRadians(getAngleFromMark(5))) * LENGTH,
                (float) getHeight() / 2 + (float) Math.sin(Math.toRadians(getAngleFromMark(5))) * LENGTH,
                paint);
    }


    private int getAngleFromMark(int mark) {
        //公式:sin(180-a)=sin a,同理cos.如果a是钝角,则180-a是锐角,此时该钝角的正弦值与锐角的正弦值是相等的.
        //所以刻度的起始角度:90 + ANGLE / 2.每一个刻度的度数为:(360 - ANGLE) / 20
        return (int) (90 + (float) ANGLE / 2 + (360 - (float) ANGLE) / 20 * mark);
    }
}
