package com.app.fitv1.ProjectUtils.BitmapUtils;

import android.animation.LayoutTransition;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import androidx.annotation.RequiresApi;

import com.app.fitv1.R;

public class SmartImageView extends FrameLayout
{

    public SmartImageView(Context context)
    {
        super(context);

        init(context, null);
    }

    public SmartImageView(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        init(context, attrs);
    }

    public SmartImageView(Context context, AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public SmartImageView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes)
    {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context, attrs);
    }

    private ImageView imageView;

    private View progressBar;

    public void init(Context context, AttributeSet attrs)
    {
        this.context = context;

        imageView = new ImageView(context);
        LayoutParams params = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        params.gravity = Gravity.CENTER;
        imageView.setLayoutParams(params);

        setLayoutTransition(new LayoutTransition());

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        progressBar = inflater.inflate(R.layout.progress_dialog, null);
        progressBar.setVisibility(INVISIBLE);
        progressBar.setLayoutParams(params);

        addView(imageView);
        addView(progressBar);
    }

    private Context context;

    public ImageView getImageView()
    {
        return imageView;
    }

    public View getProgressBar()
    {
        return progressBar;
    }

    public void loadingImage(boolean loading)
    {
        if (loading) {
            imageView.setVisibility(INVISIBLE);
            progressBar.setVisibility(VISIBLE);
        }
        else {
            progressBar.setVisibility(INVISIBLE);
            imageView.setVisibility(VISIBLE);
        }
    }

}