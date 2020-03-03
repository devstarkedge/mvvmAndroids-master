package com.app.fitv1.Base.Contract;

import android.content.Context;

import com.app.fitv1.ProjectUtils.SharedPrefHelper;

/**
 * Android contract for every MVP View
 */
public interface ViewableNoPresenter
{

    /**
     * @return {@link SharedPrefHelper} object,contains ref to all local store data in {@link android.content.SharedPreferences}.
     */
    SharedPrefHelper getLocalData();

    /**
     * @return {@link Context} of current activity.
     */
    Context getActivityG();

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
