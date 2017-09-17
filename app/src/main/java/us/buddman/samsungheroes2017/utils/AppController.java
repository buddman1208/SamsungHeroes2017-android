package us.buddman.samsungheroes2017.utils;

import android.app.Application;
import android.content.Context;


/**
 * Created by Junseok Oh on 2017-07-09.
 */

public class AppController extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
    }

    private static Context mContext;

    public static Context getContext() {
        return mContext;
    }
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(LocaleHelper.onAttach(base, "en"));
    }
}
