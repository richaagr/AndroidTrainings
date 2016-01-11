package android.mobile.com.androidtrainings.activities;

import android.app.Activity;
import android.mobile.com.androidtrainings.R;
import android.mobile.com.androidtrainings.asynctasks.TextChangerTask;
import android.os.Bundle;
import android.view.View;

public class AsyncTaskDemoActivity extends Activity {

  private TextChangerTask textChangerTask;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_async_task_demo);
    textChangerTask = new TextChangerTask(this);
    textChangerTask.execute("I am the changed text");
  }

  public void cancelTask(View view) {
    textChangerTask.cancel(true);
  }
}
