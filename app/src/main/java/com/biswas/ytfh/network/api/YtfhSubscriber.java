package com.biswas.ytfh.network.api;


import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;

import com.biswas.ytfh.R;
import com.biswas.ytfh.app.CommonMethod;
import com.biswas.ytfh.network.response.YtfhResponse;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public abstract class YtfhSubscriber<T> implements Observer<T> {
    private Context mContext;
    private boolean manageEroor;

    public YtfhSubscriber(Context context, boolean manageError) {
        this.manageEroor = manageError;
        this.mContext = context;
        if (context instanceof Activity && manageError)
            this.manageEroor = true;
        else this.manageEroor = false;
    }


    @Override
    public void onSubscribe(@NonNull Disposable d) {

    }

    @Override
    public void onNext(T t) {
        onComplete();
        YtfhResponse response = (YtfhResponse) t;
        if (response.mCategories.size() > 0 || response.mRankings.size() > 0) {

        } else
            CommonMethod.showOkAlertDialog(mContext.getResources().getText(R.string.no_data_found_try_again).toString(), mContext.getResources().getString(R.string.app_name)
                    , mContext, true);
    }

    @Override
    public void onError(Throwable e) {
        onComplete();
        if (manageEroor) {
            CommonMethod.showOkAlertDialog(mContext.getResources().getText(R.string.network_error).toString(), mContext.getResources().getString(R.string.app_name)
                    , mContext, true);
        }
    }

    @Override
    public abstract void onComplete();
}
