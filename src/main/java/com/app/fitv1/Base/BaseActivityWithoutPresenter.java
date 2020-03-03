package com.app.fitv1.Base;

import android.app.ProgressDialog;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.app.fitv1.Base.Contract.ViewableNoPresenter;
import com.app.fitv1.ProjectUtils.SharedPrefHelper;
import com.app.fitv1.R;
import com.google.android.material.snackbar.Snackbar;


public abstract class BaseActivityWithoutPresenter extends AppCompatActivity implements ViewableNoPresenter
{

    private ProgressDialog progressDialog;
    private String progressMessage = "Please wait";
    private String progressTitle   = "";

    /**
     * {@inheritDoc}
     */


    public void setupToolbar(String title)
    {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        TextView viewById = (TextView) toolbar.findViewById(R.id.title);
        if (viewById != null) {
            viewById.setText(title);
        }
        else {
            getSupportActionBar().setTitle(title);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public void displayError(String message)
    {
        if (setParentView() != null) {
            Snackbar.make(setParentView(), message, Snackbar.LENGTH_LONG).show();
        }
    }

    public void setProgressMessage(String progressMessage)
    {
        this.progressMessage = progressMessage;
    }

    public void setProgressTitle(String progressTitle)
    {
        this.progressTitle = progressTitle;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void showLoading(String progressMessage)
    {
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(getActivityG());
            progressDialog.setTitle(progressTitle);
            progressDialog.setMessage(progressMessage);
        }
        progressDialog.show();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void hideLoading()
    {
        if (progressDialog != null) {
            progressDialog.dismiss();
        }
    }




    SharedPrefHelper sharedPrefHelper;

    @Override
    public SharedPrefHelper getLocalData()
    {
        if (sharedPrefHelper == null) {
            sharedPrefHelper = new SharedPrefHelper(getApplicationContext());
        }
        return sharedPrefHelper;
    }



    protected View setParentView()
    {
        return findViewById(android.R.id.content);
    }



}
