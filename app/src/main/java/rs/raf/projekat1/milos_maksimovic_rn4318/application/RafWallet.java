package rs.raf.projekat1.milos_maksimovic_rn4318.application;

import android.app.Application;

import timber.log.Timber;

public class RafWallet extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Timber.plant(new Timber.DebugTree());
    }
}
