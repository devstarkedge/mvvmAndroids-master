package com.app.fitv1.ProjectUtils.BitmapUtils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.databinding.BindingAdapter;

import com.app.fitv1.ProjectUtils.BaseCallBack;
import com.app.fitv1.WebServices.Web;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.request.transition.Transition;

import static com.bumptech.glide.request.RequestOptions.circleCropTransform;

public class ImageLoader
{
    public static void setImageResized(final Context con, String url, int width, int height, final BaseCallBack<Bitmap> baseCallBack) {
        if (url.isEmpty()) {
            return;
        }

        if (!url.startsWith("http")) {
            url = Web.Path.BASE_IMAGE_URL + url;
        }

        Glide.with(con)
                .asBitmap()
                .load(url)
                .apply(circleCropTransform().override(width, height))
                .into(new SimpleTarget<Bitmap>()
                {
                    @Override
                    public void onResourceReady(Bitmap resource, Transition<? super Bitmap> transition) {
                        baseCallBack.onCallBack(resource);
                    }
                });

    }

    @BindingAdapter({"bind:url"})
    public static void setImageSmall(ImageView imageView, String url) {
        if (url.isEmpty()) {
            return;
        }
        if (!url.startsWith("http")) {
            url = Web.Path.BASE_IMAGE_URL + url;
        }
        Glide.with(imageView.getContext())
                .load(url)
                .apply(new RequestOptions().diskCacheStrategy(DiskCacheStrategy.ALL).override(200, 200).fitCenter())
                .into(imageView);
    }
    @BindingAdapter({"bind:url"})
    public static void setImageSmallCentreCrop(ImageView imageView, String url) {
        if (url.isEmpty()) {
            return;
        }
        if (!url.startsWith("http")) {
            url = Web.Path.BASE_IMAGE_URL + url;
        }
        Glide.with(imageView.getContext())
                .load(url)
                .apply(new RequestOptions().diskCacheStrategy(DiskCacheStrategy.ALL).override(200, 200).centerCrop())
                .into(imageView);
    }

    @BindingAdapter({"bind:uri"})
    public static void setImage(ImageView imageView, Uri url) {
        Glide.with(imageView.getContext())
                .load(url)
                .apply(circleCropTransform().override(600))
                .into(imageView);
    }


    public static void setImageComment(ImageView imageView, Uri url) {
        Glide.with(imageView.getContext())
                .load(url)
                .into(imageView);
    }


    @BindingAdapter({"bind:urlRound"})
    public static void setImageRoundSmall(ImageView imageView, String url) {
        if (url.isEmpty()) {
            return;
        }
        if (!url.startsWith("http")) {
            url = Web.Path.BASE_IMAGE_URL + url;
        }
        Glide.with(imageView.getContext())
                .load(url)
                .apply(circleCropTransform().override(200))
                .into(imageView);
    }

    @BindingAdapter({"bind:bigUrl"})
    public static void setImageBig(ImageView imageView, String url) {
        if (url == null || url.isEmpty()) {
            return;
        }
        if (!url.startsWith("http")) {
            url = Web.Path.BASE_IMAGE_URL + url;
        }
        Glide.with(imageView.getContext())
                .load(url)
                .apply(new RequestOptions().optionalCenterCrop().diskCacheStrategy(DiskCacheStrategy.ALL).override(imageView.getWidth(), imageView.getHeight()))
                .into(imageView);
    }

    public static void setImageBig_FitCenter(ImageView imageView, String url) {
        if (url == null || url.isEmpty()) {
            return;
        }
        if (!url.startsWith("http")) {
            url = Web.Path.BASE_IMAGE_URL + url;
        }
        Glide.with(imageView.getContext())
                .load(url)
                .apply(new RequestOptions().fitCenter().diskCacheStrategy(DiskCacheStrategy.ALL).override(imageView.getWidth(), imageView.getHeight()))
                .into(imageView);
    }

    public static void setImageUrl_fitXy(ImageView imageView, String url) {
        if (url == null || url.isEmpty()) {
            return;
        }
        if (!url.startsWith("http")) {
            url = Web.Path.BASE_IMAGE_URL + url;
        }
        Glide.with(imageView.getContext())
                .load(url)
                .apply(new RequestOptions().diskCacheStrategy(DiskCacheStrategy.ALL).override(imageView.getWidth(), imageView.getHeight()))
                .into(imageView);
    }

    @BindingAdapter({"bind:smartImageUrl"})
    public static void setImageURLSmartImageView(final SmartImageView smartImageView, String url) {
        if (url.isEmpty()) {
            return;
        }
        if (!url.startsWith("http")) {
            url = Web.Path.BASE_IMAGE_URL + url;
        }
        smartImageView.loadingImage(true);

        Glide.with(smartImageView.getContext())
                .load(url)
                .apply(new RequestOptions().optionalCenterCrop().diskCacheStrategy(DiskCacheStrategy.ALL).override(smartImageView.getImageView().getWidth(), smartImageView.getImageView().getHeight()))
                .listener(new RequestListener<Drawable>()
                {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        smartImageView.loadingImage(false);
                        return false;
                    }
                })
                .into(smartImageView.getImageView());
    }

}
