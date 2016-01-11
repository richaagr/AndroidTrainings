package android.mobile.com.androidtrainings.activities;

import android.app.Activity;
import android.content.Intent;
import android.mobile.com.androidtrainings.R;
import android.mobile.com.androidtrainings.services.IntentServiceIntentRedelivery;
import android.mobile.com.androidtrainings.services.IntentServiceNonSticky;
import android.mobile.com.androidtrainings.services.SimpleServiceIntentRedelivery;
import android.mobile.com.androidtrainings.services.SimpleServiceNonSticky;
import android.mobile.com.androidtrainings.services.SimpleServiceSticky;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class ServicesDemoActivity extends Activity {

  public static final String TAG = ServicesDemoActivity.class.getName();

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_services_demo);
  }

  public void buttonClicked(View view) {
    switch (view.getId()) {
      case R.id.btnNonStickyService:
        startService(IntentServiceNonSticky.class);
        break;

      case R.id.btnServiceIntentRedelivery:
        startService(IntentServiceIntentRedelivery.class);
        break;

      case R.id.btnSimpleServiceNonSticky:
        startService(SimpleServiceNonSticky.class);
        break;

      case R.id.btnSimpleServiceSticky:
        startService(SimpleServiceSticky.class);
        break;

      case R.id.btnSimpleServiceIntentRedelivery:
        startService(SimpleServiceIntentRedelivery.class);
        break;
    }
  }

  private void startService(Class<?> serviceClass) {
    Log.d(TAG, "Going to start service" + serviceClass.getName());
    startService(new Intent(this, serviceClass));
  }
}
