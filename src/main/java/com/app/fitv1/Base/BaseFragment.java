package com.app.fitv1.Base;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;

import com.app.fitv1.Base.Contract.Presentable;
import com.app.fitv1.Base.Contract.Viewable;
import com.app.fitv1.ProjectUtils.SharedPrefHelper;
import com.google.android.material.snackbar.Snackbar;


public abstract class BaseFragment<B extends ViewDataBinding, T extends Presentable> extends Fragment implements Viewable<T>
{
    protected T presenter;
    protected B binding;
    protected View view;
    protected SharedPrefHelper sharedPrefHelper;
    private ProgressDialog progressDialog;

    /**
     * {@inheritDoc}
     */
    @Override
    public void onStart() {
        super.onStart();
        getPresenter().onStart();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getView() == null) {
            onCreateFragmentG();
        }

        setRetainInstance(true);
    }

    @Override
    public SharedPrefHelper getLocalData() {
        if (sharedPrefHelper == null) {
            sharedPrefHelper = new SharedPrefHelper(getActivityG());
        }
        return sharedPrefHelper;
    }

    @Override
    public Context getActivityG() {
        return getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false);
        view = binding.getRoot();

        if (getView() == null) {
            onCreateFragmentG();
        }

        return view;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getPresenter().onViewCreated();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onDestroyView() {
        getPresenter().detachView();
        super.onDestroyView();
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

    @Override
    public void onResume() {
        getPresenter().onResume();
        super.onResume();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void displayError(String message) {
        if (getParentView() != null) {
            Toast.makeText(getActivityG(), message, Toast.LENGTH_SHORT).show();
            //Snackbar.make(setParentView(), message, Snackbar.LENGTH_LONG).show();
        }
    }

    public View getParentView() {
        return view;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void showLoading(String progressMessage) {
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(getActivityG());
            progressDialog.setMessage(progressMessage);
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

    public B getDataBinder() {
        return binding;
    }


    @LayoutRes
    protected abstract int getLayoutId();

    protected abstract void onCreateFragmentG();

}
