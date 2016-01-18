package android.mobile.com.androidtrainings.activities;

import android.app.Activity;
import android.content.Intent;
import android.mobile.com.androidtrainings.R;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
  }

  public void buttonClicked(View view) {
    switch (view.getId()) {
      case R.id.btnAsyncTasksDemo:
        startActivity(new Intent(this, AsyncTaskDemoActivity.class));
        break;
      case R.id.btnServicesDemo:
        startActivity(new Intent(this, ServicesDemoActivity.class));
        break;
      case R.id.btnRemoteServiceMessenger:
        startActivity(new Intent(this, RemoteServiceMessengerDemo.class));
        break;
      case R.id.btnRemoteServiceAidl:
        startActivity(new Intent(this, RemoteServiceAIDLDemo.class));
        break;
    }
  }
}
