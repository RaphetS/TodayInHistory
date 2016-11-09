package org.raphets.todayinhistory.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

import org.raphets.todayinhistory.utils.DanweiUtil;

/**
 * Created by RaphetS on 2016/10/16.
 */

public class CircleView extends View {
    private Paint mPaint;
    private Paint mStrokePaint;
    private int mRadius;
    private Paint mDotLinePaint;

    public CircleView(Context context) {
        super(context);
        init();
    }


    public CircleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CircleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint=new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.RED);

        mStrokePaint=new Paint();
        mStrokePaint.setAntiAlias(true);
        mStrokePaint.setColor(Color.RED);
        mStrokePaint.setStyle(Paint.Style.STROKE);
        mStrokePaint.setStrokeWidth(3);

        mDotLinePaint=new Paint();
        mDotLinePaint.setAntiAlias(true);
        mDotLinePaint.setColor(Color.RED);
        mDotLinePaint.setStyle(Paint.Style.STROKE);
        mDotLinePaint.setStrokeWidth(4);
        mDotLinePaint.setPathEffect(new DashPathEffect( new float [ ] { 5, 4 }, 0 ) );

        mRadius= DanweiUtil.dp2px(getContext(),6);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthMode=MeasureSpec.getMode(widthMeasureSpec);
        int widthSize=MeasureSpec.getSize(widthMeasureSpec);
        int heightMode=MeasureSpec.getMode(heightMeasureSpec);
        int heightSize=MeasureSpec.getSize(heightMeasureSpec);

        if (widthMode==MeasureSpec.UNSPECIFIED){
            widthSize= DanweiUtil.dp2px(getContext(),20);
        }
        if (heightMode==MeasureSpec.UNSPECIFIED){
            heightSize= DanweiUtil.dp2px(getContext(),20);
        }

        setMeasuredDimension(widthSize,heightSize);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawLine(getWidth()/2,0,getWidth()/2,getHeight()/5,mStrokePaint);
        canvas.drawCircle(getWidth()/2,getHeight()/5+mRadius,mRadius*2/3,mPaint);
       // canvas.drawLine(getWidth()/2+mRadius+DanweiUtil.dp2px(getContext(),3),getHeight()/4+mRadius,getWidth(),getHeight()/4+mRadius,mDotLinePaint);
        Path path=new Path();
        path.moveTo(getWidth()/2+mRadius+ DanweiUtil.dp2px(getContext(),3),getHeight()/5+mRadius);
        path.lineTo(getWidth(),getHeight()/5+mRadius);
        canvas.drawPath(path,mDotLinePaint);
        canvas.drawCircle(getWidth()/2,getHeight()/5+mRadius,mRadius,mStrokePaint);
        canvas.drawLine(getWidth()/2,getHeight()/5+2*mRadius,getWidth()/2,getHeight(),mStrokePaint);
    }
}
