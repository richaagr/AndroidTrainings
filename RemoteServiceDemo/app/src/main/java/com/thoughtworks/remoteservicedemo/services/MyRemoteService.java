package com.thoughtworks.remoteservicedemo.services;

import android.app.Service;
import android.content.Intent;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Messenger;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.thoughtworks.remoteservicedemo.remoteobjects.MyHandler;
import com.thoughtworks.remoteservicedemo.remoteobjects.MyRemoteObject;

public class MyRemoteService extends Service {
  public static final String TAG = MyRemoteService.class.getName();
  public static final String CONNECTION_TYPE_AIDL = "AIDL";
  public static final String TYPE_OF_CONNECTION = "TYPE_OF_CONNECTION";
  public static final Messenger MESSENGER;

  static {
    HandlerThread myHandlerThread = new HandlerThread("MyHandlerThread");
    myHandlerThread.start();
    MESSENGER = new Messenger(new MyHandler(myHandlerThread.getLooper()));
  }

  @Nullable
  @Override
  public IBinder onBind(Intent intent) {
    if(CONNECTION_TYPE_AIDL.equalsIgnoreCase(intent.getStringExtra(TYPE_OF_CONNECTION))) {
      return getRemoteObjectAsBinder();
    }
    return getBinderFromMessenger();
  }

  @Override
  public boolean onUnbind(Intent intent) {
    Log.d(TAG, "onUnbind() called");
    return super.onUnbind(intent);
  }

  @NonNull
  private IBinder getRemoteObjectAsBinder() {
    MyRemoteObject myRemoteObject = new MyRemoteObject();
    Log.d(TAG, "onBind() called and returning " + myRemoteObject);
    return myRemoteObject;
  }

  private IBinder getBinderFromMessenger() {
    Log.d(TAG, "onBind() called and returning messenger");
    return MESSENGER.getBinder();
  }

}
