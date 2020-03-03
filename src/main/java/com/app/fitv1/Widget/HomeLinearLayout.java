/*
 * Copyright (c) 2017. Code by PRM . Happy coding
 */

package com.app.fitv1.Widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

public class HomeLinearLayout extends LinearLayout
{
    public HomeLinearLayout(Context context)
    {
        super(context);
    }

    public HomeLinearLayout(Context context, AttributeSet attrs)
    {
        super(context, attrs);
    }

    public HomeLinearLayout(Context context, AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
    {
        int i = Math.round(widthMeasureSpec/2);
        super.onMeasure(widthMeasureSpec, i );
    }
}
