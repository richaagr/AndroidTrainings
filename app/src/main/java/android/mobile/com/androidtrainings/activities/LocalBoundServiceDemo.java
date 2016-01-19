package android.mobile.com.androidtrainings.activities;

import android.app.Activity;
import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.mobile.com.androidtrainings.R;
import android.mobile.com.androidtrainings.services.BoundService;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class LocalBoundServiceDemo extends Activity implements ServiceConnection {

  public static final String TAG = LocalBoundServiceDemo.class.getName();
  private BoundService boundService;
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
    Intent intent = new Intent(this, BoundService.class);
    bindService(intent, this, Service.BIND_AUTO_CREATE);
  }

  @Override
  protected void onStop() {
    unbindService(this);
    super.onStop();
  }

  public void buttonClicked(View view) {
    switch (view.getId()) {
      case R.id.btnSendMessage:
        if (boundService != null)
          boundService.printMessage(sendMessageEditText.getText().toString());
        break;
      case R.id.btnGetCount:
        if (boundService != null)
          countTextView.setText(String.format("Message Count from Service : %d", boundService.getCount()));
        break;
    }
  }

  @Override
  public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
    Log.d(TAG, "onServiceConnected() got iBinder: " + iBinder);
    boundService = ((BoundService.MyObject) iBinder).getService();
    Log.d(TAG, "boundService retrieved is: " + boundService);
  }

  @Override
  public void onServiceDisconnected(ComponentName componentName) {
    boundService = null;
  }
}
