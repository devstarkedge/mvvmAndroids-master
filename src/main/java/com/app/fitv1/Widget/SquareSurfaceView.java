package com.app.fitv1.Widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.SurfaceView;

/**
 * created by PARAMBIR SINGH on 1/2/18.
 */

public class SquareSurfaceView extends SurfaceView
{
    public SquareSurfaceView(Context context) {
        super(context);
    }

    public SquareSurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SquareSurfaceView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public SquareSurfaceView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, widthMeasureSpec);
    }
}
