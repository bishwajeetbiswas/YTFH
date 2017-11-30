package com.biswas.ytfh.app;

import android.app.Application;
import android.content.Context;
import android.graphics.Typeface;
import android.util.Log;

import com.biswas.ytfh.BuildConfig;
import com.biswas.ytfh.network.api.ApiClient;
import com.biswas.ytfh.network.api.YtfhApi;

import timber.log.Timber;

public class YtfhApplication extends Application {

    private static YtfhApplication mInstance;
    private Typeface ytfhFontRegular = null;
    private Typeface ytfhFontRegularBold = null;
    private Typeface ytfhFontMedium = null;
    YtfhApi mYtfhApiService = null;


    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        initTimber();
    }


    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
    }

    private void initTimber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        } else {
            Timber.plant(new CrashReportingTree());
        }
    }

    private static class CrashReportingTree extends Timber.Tree {
        @Override
        protected void log(int priority, String tag, String message, Throwable t) {
            if (priority == Log.VERBOSE || priority == Log.DEBUG) {
                return;
            }

            if (t != null) {
                if (priority == Log.ERROR) {
                    //  FakeCrashLibrary.logError(t);
                } else if (priority == Log.WARN) {
                    // FakeCrashLibrary.logWarning(t);
                }
            }
        }
    }

    public YtfhApi getYtfhApiService() {
        if (mYtfhApiService == null) {
            mYtfhApiService = ApiClient.getInterface(this);
        }
        return mYtfhApiService;
    }

    public Typeface getYtfhFontRegular() {
        if (ytfhFontRegular == null) {
            ytfhFontRegular = Typeface.createFromAsset(this.getAssets(), "fonts/Roboto-Regular.ttf");
        }
        return ytfhFontRegular;
    }

    public Typeface getYtfhFontMedium() {
        if (ytfhFontMedium == null) {
            ytfhFontMedium = Typeface.createFromAsset(this.getAssets(), "fonts/Roboto-Medium.ttf");
        }
        return ytfhFontMedium;
    }

    public Typeface getYtfhFontBold() {
        if (ytfhFontRegularBold == null) {
            ytfhFontRegularBold = Typeface.createFromAsset(this.getAssets(), "fonts/Roboto-Bold.ttf");
        }
        return ytfhFontRegularBold;
    }


    public static synchronized YtfhApplication getInstance() {
        return mInstance;
    }
}
