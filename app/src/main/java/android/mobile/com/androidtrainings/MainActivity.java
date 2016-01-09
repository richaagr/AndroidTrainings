package android.mobile.com.androidtrainings;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

  private TextChangerTask textChangerTask;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    textChangerTask = new TextChangerTask(this);
    textChangerTask.execute("I am the changed text");
  }

  public void cancelTask(View view) {
    textChangerTask.cancel(true);
  }
}
