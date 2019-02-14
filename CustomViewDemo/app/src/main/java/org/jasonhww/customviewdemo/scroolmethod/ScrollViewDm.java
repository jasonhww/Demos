package org.jasonhww.customviewdemo.scroolmethod;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Scroller;

public class ScrollViewDm extends View {
    private static final String TAG = "ScrollViewDm";
    private int lastX, lastY;
    private Scroller mScroller;

    public ScrollViewDm(Context context) {
        super(context);
        init(context);
    }


    public ScrollViewDm(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        mScroller = new Scroller(context);
    }


    @Override
    public void computeScroll() {
        if (mScroller.computeScrollOffset()) {
            ((View) getParent()).scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
            invalidate();
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        //处理wrap_content,设置一个默认的宽或高
        if (widthMode == MeasureSpec.AT_MOST && heightMode == MeasureSpec.AT_MOST) {
            setMeasuredDimension(200, 200);
        } else if (widthMode == MeasureSpec.AT_MOST) {
            setMeasuredDimension(200, heightSize);
        } else if (heightMode == MeasureSpec.AT_MOST) {
            setMeasuredDimension(widthSize, 200);
        }

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //获取触摸点位置坐标
        int x = (int) event.getX();
        int y = (int) event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                //保存DOWN时的坐标
                lastX = x;
                lastY = y;
                break;
            case MotionEvent.ACTION_MOVE:
                int offsetX = x - lastX;
                int offsetY = y - lastY;
                scrollMethodOnScrollBy(-offsetX, -offsetY);
                //scrollMethodOnLayout(offsetX, offsetY);
                //scrollMethodOnLP(offsetX, offsetY);
                break;
            case MotionEvent.ACTION_UP:
                //滑动到最初位置,需要通过父容器调用
                scrollMethodOnScroller(-((View) getParent()).getScrollX(), -((View) getParent()).getScrollY());
                break;
            default:
                break;
        }

        return true;
    }

    private void scrollMethodOnScrollBy(int offsetX, int offsetY) {
        //滑动的是View的内容,需要通过父容器调用
        ((View) getParent()).scrollBy(offsetX, offsetY);
    }

    private void scrollMethodOnLayout(int offsetX, int offsetY) {
        //layout(getLeft() + offsetX, getTop() + offsetY, getRight() + offsetX, getBottom() + offsetY);
        offsetLeftAndRight(offsetX);
        offsetTopAndBottom(offsetY);
    }

    private void scrollMethodOnLP(int offsetX, int offsetY) {
        //在不清楚父View是什么类型时,可以使用ViewGroup.MarginLayoutParams
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) getLayoutParams();
        layoutParams.leftMargin += offsetX;
        layoutParams.topMargin += offsetY;
        setLayoutParams(layoutParams);
    }


    private void scrollMethodOnScroller(int offsetX, int offsetY) {
        mScroller.startScroll(((View) getParent()).getScrollX(), ((View) getParent()).getScrollY(),
                offsetX, offsetY, 3000);
        invalidate();
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

    }


    @Override
    public void onDrawForeground(Canvas canvas) {
        super.onDrawForeground(canvas);
    }
}
