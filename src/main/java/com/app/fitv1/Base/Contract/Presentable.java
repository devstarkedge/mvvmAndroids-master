package com.app.fitv1.Base.Contract;


import androidx.annotation.NonNull;

/**
 * Android contract for every MVP Presenter
 */
public interface Presentable<V extends Viewable> {

    /**
     * Every Presentable must implement onStart state
     */
    void onStart();

    /**
     * Every Presentable must implement onViewCreated state
     */
    void onViewCreated();

    /**
     * Every Presentable must implement onResume state
     */
    void onResume();


    /**
     * Every Presentable must implement onPause state
     */
    void onPause();


    /**
     * Every Presentable must implement onStop state
     */
    void onStop();


    /**
     * Every Presentable must attach a Viewable
     *
     * @param viewable Viewable
     */
    void attachView(@NonNull V viewable);


    /**
     * Every Presentable must detach its Viewable
     */
    void detachView();


    /**
     * Every Presentable must be able to access to its attached View
     *
     * @return V Viewable
     */
    V getView();

}
