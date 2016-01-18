package com.thoughtworks.remoteservicedemo.remoteobjects;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;
import android.util.Log;

import static com.thoughtworks.remoteservicedemo.remoteobjects.MyHandler.MessageType.GET_COUNT;

public class MyHandler extends Handler {
  private int msgCount = 0;

  public static final String TAG = MyHandler.class.getName();

  public MyHandler(Looper looper) {
    super(looper);
  }

  @Override
  public void handleMessage(Message msg) {
    Log.d(TAG, "Message received from client of type " + msg.what);

    if (MessageType.PRINT_MESSAGE.equals(msg.what)) {
      Bundle data = msg.getData();
      Log.d(TAG, String.format("Message: %s in Thread: %s", data.getString("MSG"), Thread.currentThread().getName()));
      msgCount++;
    } else if (GET_COUNT.equals(msg.what)) {
      try {
        Message replyMessage = Message.obtain(msg);
        Bundle bundle = new Bundle();
        bundle.putInt("COUNT", msgCount);
        replyMessage.setData(bundle);
        msg.replyTo.send(replyMessage);
      } catch (RemoteException e) {
        Log.e(TAG, "Unable to reply back to client", e);
      }
    }
  }

  enum MessageType {
    PRINT_MESSAGE(0), GET_COUNT(1);

    private int index;

    MessageType(int index) {
      this.index = index;
    }

    public boolean equals(int typeOfMessage) {
      return index == typeOfMessage;
    }
  }
}
