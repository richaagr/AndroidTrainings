package android.mobile.com.androidtrainings.activities;

import android.app.Activity;
import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.mobile.com.androidtrainings.R;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.thoughtworks.remoteservicedemo.remoteobjects.IRemoteObject;

public class RemoteServiceAIDLDemo extends Activity implements ServiceConnection {

  public static final String TAG = RemoteServiceAIDLDemo.class.getName();
  private IRemoteObject remoteObject;
  private EditText sendMessageEditText;
  private TextView countTextView;

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
          if(remoteObject!=null)
            remoteObject.printMessage(sendMessageEditText.getText().toString());
          break;
        case R.id.btnGetCount:
          if(remoteObject!=null)
            countTextView.setText(String.format("Message Count from Service : %d", remoteObject.getCount()));
          break;
      }
    } catch (RemoteException e) {
      Log.e(TAG, e.getMessage(), e);
    }
  }

  @Override
  public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
    Log.d(TAG, "onServiceConnected() got iBinder: " + iBinder);
    remoteObject = IRemoteObject.Stub.asInterface(iBinder);
    Log.d(TAG, "remoteObject retrieved is: " + remoteObject);
  }

  @Override
  public void onServiceDisconnected(ComponentName componentName) {
    remoteObject = null;
  }
}
