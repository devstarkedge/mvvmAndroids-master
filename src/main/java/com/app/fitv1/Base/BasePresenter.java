package com.app.fitv1.Base;

import androidx.annotation.NonNull;

import com.app.fitv1.Base.Contract.Presentable;
import com.app.fitv1.Base.Contract.Viewable;
import com.app.fitv1.ProjectUtils.BaseCallBack;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class BasePresenter<T extends Viewable> implements Presentable<T>
{
    private T viewable;
    //for multiple request we can use compositeDisposable,
    // but currently we use Disposable,as there can be only one request.
    protected Disposable compositeDisposable;

    public Disposable getDisposable() {
        return compositeDisposable;
    }

    public void clearSubscriptions() {
        if (getDisposable() != null) {
            getDisposable().dispose();
        }
    }

    @Override
    public void onStart() {
        // No-op by default
    }

    @Override
    public void onViewCreated() {
//        views are created ,now its time to initialize them..
        if (getView() != null) {
            getView().initViews();
        }
    }

    @Override
    public void onResume() {
        // No-op by default
    }

    @Override
    public void onPause() {
        // No-op by default
    }

    @Override
    public void onStop() {
        // No-op by default
    }

    @Override
    public void attachView(@NonNull T viewable) {
        this.viewable = viewable;
    }

    @Override
    public void detachView() {
        clearSubscriptions();
        this.viewable = null;
    }

    public <V> void createApiRequest(Observable<V> observables, final BaseCallBack<V> callBack) {
        compositeDisposable = (observables
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<V>()
                {
                    @Override
                    public void onNext(@io.reactivex.annotations.NonNull V s) {
                        callBack.onCallBack(s);
                    }

                    @Override
                    public void onError(@io.reactivex.annotations.NonNull Throwable e) {
                        getView().displayError(e.getMessage());
                        getView().hideLoading();
                    }

                    @Override
                    public void onComplete() {

                    }
                }));
    }

    @Override
    public T getView() {
        return viewable;
    }
}
