package org.jasonhww.customviewdemo.hcp.draw.chap7;

import android.content.Context;
import android.graphics.Camera;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import org.jasonhww.customviewdemo.hcp.draw.utils.Utils;

/**
 * Camera 3D效果的实现
 */
public class CameraView extends View {

    private static final String TAG = "CameraView";
    private static final int width = 400;
    private static final int bmLeft = 100, bmTop = 100;

    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Camera camera = new Camera();

    public CameraView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    {
        paint.setStyle(Paint.Style.FILL);
        paint.setStrokeWidth(Utils.dp2px(5));
        paint.setColor(Color.BLACK);

        camera.rotateX(45);
        camera.setLocation(0, 0, Utils.getZForCamera()); // -8 = -8 * 72
    }


    @Override
    protected void onDraw(Canvas canvas) {
        //两次绘制之间不影响顺序.
        super.onDraw(canvas);

        //绘制上半部分,先弄明白下半部分,上半部分依葫芦哦画瓢
        canvas.save();
        canvas.translate(bmLeft + width / 2, bmTop + width / 2);
        canvas.rotate(-30);

        //canvas.clipRect(-width / 2, -width / 2, width / 2, 0);//裁剪出上半部分
        canvas.clipRect(-width, -width, width, 0);//裁剪出上半部分

        canvas.rotate(30);
        canvas.translate(-(bmLeft + width / 2), -(bmTop + width / 2));
        canvas.drawBitmap(Utils.getAvatar(getResources(), width), bmLeft, bmTop, paint);//1.画图
        canvas.restore();


        /**
         * 三维变换,采用倒序,记住一个定律,"单坐标系,操作的是View,不是canvas".
         * 例如将canvas进行translate操作时,canvas坐标不动,移动的是View.
         * 这样容易理解.
         * 三维变换本质基本上就是沿着x轴与Y轴旋转
         */
        //绘制下半部分
        canvas.save();
        canvas.translate(bmLeft + width / 2, bmTop + width / 2);//5.移动回去
        canvas.rotate(-30);

        camera.applyToCanvas(canvas);//4.应用到画布
        //canvas.clipRect(-width / 2, 0, width / 2, width / 2);//3.裁剪出下半部分
        canvas.clipRect(-width, 0, width, width);//3.裁剪出下半部分,加了旋转后范围变大,所以加大一倍？

        canvas.rotate(30);
        canvas.translate(-(bmLeft + width / 2), -(bmTop + width / 2));//2.将原点移动到图片中心点位置.假如画布不移动,图片移动了.
        canvas.drawBitmap(Utils.getAvatar(getResources(), width), bmLeft, bmTop, paint);//1.画图
        canvas.restore();
    }


}
