package com.thoughtworks.remoteservicedemo.services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import com.thoughtworks.remoteservicedemo.remoteobjects.MyRemoteObject;

public class MyRemoteService extends Service {
  public static final String TAG = MyRemoteService.class.getName();

  @Nullable
  @Override
  public IBinder onBind(Intent intent) {
    MyRemoteObject myRemoteObject = new MyRemoteObject();
    Log.d(TAG, "onBind() called and returning " + myRemoteObject);
    return myRemoteObject;
  }

  @Override
  public boolean onUnbind(Intent intent) {
    Log.d(TAG, "onUnbind() called");
    return super.onUnbind(intent);
  }

}
