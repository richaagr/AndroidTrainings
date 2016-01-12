package android.mobile.com.androidtrainings.services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

public class SimpleServiceIntentRedelivery extends Service {
  public static final String TAG = SimpleServiceIntentRedelivery.class.getName();

  @Nullable
  @Override
  public IBinder onBind(Intent intent) {
    return null;
  }

  @Override
  public void onCreate() {
    Log.d(TAG, "onCreate() in Thread : " + Thread.currentThread().getName());
    super.onCreate();
  }

  @Override
  public int onStartCommand(Intent intent, int flags, int startId) {
    Log.d(TAG, "onStartCommand() with startId " + startId + ", intent: " + intent + " in Thread : " + Thread.currentThread().getName());
    super.onStartCommand(intent, flags, startId);
    return START_REDELIVER_INTENT;
  }

  @Override
  public void onDestroy() {
    Log.d(TAG, "onDestroy() in Thread : " + Thread.currentThread().getName());
    super.onDestroy();
  }
}
