package android.mobile.com.androidtrainings.services;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

public class BoundService extends Service {
  private int msgCount = 0;

  public static final String TAG = BoundService.class.getName();

  @Nullable
  @Override
  public IBinder onBind(Intent intent) {
    return new MyObject();
  }

  public void printMessage(String msg) {
    Log.d(TAG, String.format("Message: %s in Thread: %s", msg, Thread.currentThread().getName()));
    msgCount++;
  }

  public int getCount() {
    return msgCount;
  }

  public class MyObject extends Binder {
    public BoundService getService() {
      return BoundService.this;
    }
  }
}
