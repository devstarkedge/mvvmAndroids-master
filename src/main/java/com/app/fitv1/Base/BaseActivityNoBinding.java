package com.app.fitv1.Base;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.app.fitv1.Base.Contract.Presentable;
import com.app.fitv1.Base.Contract.Viewable;
import com.app.fitv1.ProjectUtils.SharedPrefHelper;
import com.app.fitv1.R;
import com.google.android.material.snackbar.Snackbar;

public abstract class BaseActivityNoBinding<T extends Presentable> extends AppCompatActivity implements Viewable<T> {
    protected T presenter;
    private ProgressDialog progressDialog;

    /**
     * {@inheritDoc}
     */
    @Override
    public void onStart() {
        super.onStart();
        getPresenter().onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        getPresenter().onResume();
    }

    @Override
    protected void onPause() {
        getPresenter().onPause();
        super.onPause();
    }

    public void setupToolbar(String title) {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        TextView viewById = (TextView) toolbar.findViewById(R.id.title);

        if (viewById != null) {
            viewById.setText(title);
        } else {
            getSupportActionBar().setTitle(title);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(setLayoutId());
        onCreateActivityG();
        getPresenter().onViewCreated();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onStop() {
        getPresenter().onStop();
        super.onStop();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onDestroy() {
        getPresenter().detachView();
        presenter = null;
        super.onDestroy();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void displayError(String message) {
        if (setParentView() != null) {
            Snackbar.make(setParentView(), message, Snackbar.LENGTH_LONG).show();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void showLoading(String progressMessage) {
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(getActivityG());
            progressDialog.setTitle(getResources().getString(R.string.app_name));
            progressDialog.setMessage(progressMessage);
            progressDialog.setCancelable(false);
        }
        progressDialog.show();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void hideLoading() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public T getPresenter() {
        return presenter;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void injectPresenter(T presenter) {
        this.presenter = presenter;
    }

    @Override
    public SharedPrefHelper getLocalData() {
        return new SharedPrefHelper(getApplicationContext());
    }

    protected abstract int setLayoutId();

    protected View setParentView() {
        return findViewById(android.R.id.content);
    }

    /**
     * injectPresenter( @link Presentable);
     * attachView(this);
     */
    protected abstract void onCreateActivityG();

    public void enableFullScreenMode() {
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);
    }

    public void disableFullScreenMode() {
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
    }

}
