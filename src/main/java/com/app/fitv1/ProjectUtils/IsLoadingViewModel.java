package com.app.fitv1.ProjectUtils;


import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

public class IsLoadingViewModel extends BaseObservable
{
    private boolean isLoading;

    @Bindable
    public boolean isLoading()
    {
        return isLoading;
    }

    public void setLoading(boolean loading)
    {
        isLoading = loading;
        notifyChange();
    }
}
