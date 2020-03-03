package com.app.fitv1.Base.Contract;

import android.content.Context;

import com.app.fitv1.ProjectUtils.SharedPrefHelper;


/**
 * Android contract for every MVP View
 */
public interface Viewable<T>
{
    /**
     * initialize all views here.
     */
    void initViews();

    /**
     * @return {@link SharedPrefHelper} object,contains ref to all local store data in {@link android.content.SharedPreferences}.
     */
    SharedPrefHelper getLocalData();

    /**
     * @return {@link Context} of current activity.
     */
    Context getActivityG();

    /**
     * Every Viewable must be able to access to its attached Presenter
     *
     * @return Presentable
     */
    T getPresenter();

    /**
     * Every Viewable must be able to inject its Presenter
     *
     * @param presenter Presentable
     */
    void injectPresenter(T presenter);

    /**
     * Every Viewable must have a error message system
     */
    void displayError(String message);

    /**
     * Every Viewable must implement one show loading feature
     */
    void showLoading(String progressMessage);

    /**
     * Every Viewable must implement one hide loading feature
     */
    void hideLoading();


}
