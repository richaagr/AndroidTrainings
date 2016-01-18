package android.mobile.com.androidtrainings.activities;

import android.app.Activity;
import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.mobile.com.androidtrainings.R;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.lang.ref.WeakReference;

public class RemoteServiceMessengerDemo extends Activity implements ServiceConnection {

  public static final String TAG = RemoteServiceMessengerDemo.class.getName();
  private EditText sendMessageEditText;
  private TextView countTextView;
  private Messenger messenger;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_remote_service_demo);
    sendMessageEditText = (EditText) findViewById(R.id.edtTxtMsg);
    countTextView = (TextView) findViewById(R.id.textViewCount);
  }

  @Override
  protected void onStart() {
    super.onStart();
    Intent intent = new Intent();
    intent.setClassName("com.thoughtworks.remoteservicedemo", "com.thoughtworks.remoteservicedemo.services.MyRemoteService");
    intent.putExtra("TYPE_OF_CONNECTION", "MESSENGER");
    bindService(intent, this, Service.BIND_AUTO_CREATE);
  }

  @Override
  protected void onStop() {
    unbindService(this);
    super.onStop();
  }

  public void buttonClicked(View view) {
    try {
      switch (view.getId()) {
        case R.id.btnSendMessage:
          if (messenger != null) {
            Bundle bundle = new Bundle();
            bundle.putString("MSG", sendMessageEditText.getText().toString());
            Message msg = Message.obtain(null, 0);
            msg.setData(bundle);
            messenger.send(msg);
          }
          break;
        case R.id.btnGetCount:
          if (messenger != null) {
            Message msg = Message.obtain(null, 1);
            msg.replyTo = new Messenger(new CountUpdatingHandler(countTextView));
            messenger.send(msg);
          }
          break;
      }
    } catch (RemoteException e) {
      Log.e(TAG, e.getMessage(), e);
    }
  }

  @Override
  public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
    Log.d(TAG, "onServiceConnected() got iBinder: " + iBinder);
    messenger = new Messenger(iBinder);
  }

  @Override
  public void onServiceDisconnected(ComponentName componentName) {
    messenger = null;
  }

  private static class CountUpdatingHandler extends Handler {

    private final WeakReference<TextView> countTextView;

    public CountUpdatingHandler(TextView textView) {
      countTextView = new WeakReference<>(textView);
    }

    @Override
    public void handleMessage(Message msg) {
      TextView textView = countTextView.get();
      if(textView !=null) {
        textView.setText(String.format("Message Count from Service : %s", msg.getData().getInt("COUNT")));
      }
    }
  }

}
