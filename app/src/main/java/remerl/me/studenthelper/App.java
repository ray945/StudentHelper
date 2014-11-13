package remerl.me.studenthelper;

import android.app.Application;
import android.content.Context;

import remerl.me.studenthelper.data.RequestManager;


/**
 * Created by admin on 2014/9/21.
 */
public class App extends Application {
    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        RequestManager.init(getApplicationContext());

    }

    public static Context getContext() {
        return context;
    }

}
