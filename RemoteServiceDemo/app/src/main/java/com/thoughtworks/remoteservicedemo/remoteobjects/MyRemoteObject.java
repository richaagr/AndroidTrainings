package com.thoughtworks.remoteservicedemo.remoteobjects;

import android.util.Log;

public class MyRemoteObject extends IRemoteObject.Stub {
  private int msgCount = 0;

  public static final String TAG = MyRemoteObject.class.getName();

  @Override
  public void printMessage(String msg) {
    Log.d(TAG, msg + Thread.currentThread().getName());
    msgCount++;
  }

  @Override
  public int getCount() {
    return msgCount;
  }
}
