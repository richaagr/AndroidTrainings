package android.mobile.com.androidtrainings.services;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

public class IntentServiceNonSticky extends IntentService {

  public static final String TAG = IntentServiceNonSticky.class.getName();

  public IntentServiceNonSticky() {
    super(TAG);
  }

  @Override
  public void onCreate() {
    Log.d(TAG, "onCreate() in Thread : " + Thread.currentThread().getName());
    super.onCreate();
  }

  @Override
  public int onStartCommand(Intent intent, int flags, int startId) {
    int onStartCommand = super.onStartCommand(intent, flags, startId);
    Log.d(TAG, "onStartCommand() with startId " + startId + " in Thread : " + Thread.currentThread().getName());
    return onStartCommand;
  }

  @Override
  protected void onHandleIntent(Intent intent) {
    Log.d(TAG, "onHandleIntent()");
    for (int i = 1; i <= 10; i++) {
      Log.d(TAG, "Thread " + Thread.currentThread().getName() + " " + i);
      try {
        Thread.sleep(1000);
      } catch (InterruptedException e) {
        Log.e(TAG, "Exception", e);
      }
    }
  }

  @Override
  public void onStart(Intent intent, int startId) {
    Log.d(TAG, "onStart() in Thread : " + Thread.currentThread().getName());
    super.onStart(intent, startId);
  }

  @Override
  public void onDestroy() {
    Log.d(TAG, "onDestroy() in Thread : " + Thread.currentThread().getName());
    super.onDestroy();
  }
}
