package org.jasonhww.customviewdemo.hcp.draw.chap6;

import android.content.Context;
import android.graphics.*;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import org.jasonhww.customviewdemo.R;
import org.jasonhww.customviewdemo.hcp.draw.utils.Utils;

/**
 * 圆角图形
 * xferModer的使用
 */
public class AvatarView extends View {
    private static final float WIDTH = Utils.dp2px(80);
    private static final float PADDING = Utils.dp2px(80);
    //多出间隙宽度
    private static final float EDGE_WIDTH = Utils.dp2px(5);

    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Bitmap bitmap;
    private RectF circleRectF = new RectF();
    private PorterDuffXfermode porterDuffXfermode;

    public AvatarView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    {
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.BLACK);

        bitmap = getAvatar((int) WIDTH);
        //初始化过渡模式,SRC_IN:只绘制源图,区域为源图与目标图相交部分,
        porterDuffXfermode = new PorterDuffXfermode(PorterDuff.Mode.SRC_IN);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        circleRectF.set(PADDING, PADDING, PADDING + WIDTH, PADDING + WIDTH);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //画外圈
        canvas.drawOval(PADDING - EDGE_WIDTH, PADDING - EDGE_WIDTH,
                WIDTH + PADDING + EDGE_WIDTH, WIDTH + PADDING + EDGE_WIDTH, paint);


        int layerId = canvas.saveLayer(circleRectF, paint);//保存开启离屏缓冲
        //画内圈
        paint.setColor(Color.RED);
        canvas.drawOval(circleRectF, paint);
        //采用过渡模式画bitmap
        paint.setXfermode(porterDuffXfermode);
        canvas.drawBitmap(bitmap, PADDING, PADDING, paint);
        paint.setXfermode(null);
        canvas.restoreToCount(layerId);//恢复
    }

    Bitmap getAvatar(int width) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(getResources(), R.drawable.lhk, options);
        options.inJustDecodeBounds = false;
        options.inDensity = options.outWidth;
        options.inTargetDensity = width;

        return BitmapFactory.decodeResource(getResources(), R.drawable.lhk, options);
    }
}
