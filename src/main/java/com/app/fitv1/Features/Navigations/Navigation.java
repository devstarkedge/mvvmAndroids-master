package com.app.fitv1.Features.Navigations;

import android.content.Context;
import android.content.Intent;

import com.app.fitv1.Base.BaseActivity;
import com.app.fitv1.R;
import com.app.fitv1.databinding.ActivityNavigationsBinding;

public class Navigation extends BaseActivity<ActivityNavigationsBinding, NavigationPresenter> implements NavigationView {

    public static void start(Context context) {
        Intent starter = new Intent(context, Navigation.class);
        context.startActivity(starter);
    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_navigations;
    }

    @Override
    protected void onCreateActivityG() {
        injectPresenter(new NavigationPresenter());
        getPresenter().attachView(this);
    }

    @Override
    public void initViews() {

    }

    @Override
    public Context getActivityG() {
        return Navigation.this;
    }
}
