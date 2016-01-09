package android.mobile.com.androidtrainings;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

public class TextChangerTask extends AsyncTask<String,Integer,String> {

  private final Context context;

  public TextChangerTask(Context context) {
    this.context = context;
  }

  @Override
  protected void onPreExecute() {
    super.onPreExecute();
    Log.println(Log.INFO,"Pre Execute","Pre Execute");
  }

  @Override
  protected String doInBackground(String... params) {
      for (int i=0;i<10000000;i++){
        publishProgress(i);
      }
    Log.println(Log.INFO, "doInBackgroundOver","***********doInBackgroundOver*********");
    return params[0];
  }

  @Override
  protected void onProgressUpdate(Integer... values) {
    super.onProgressUpdate(values);
    Log.println(Log.INFO, "onProgressMethodCall","Value is "+values.toString());
  }

  @Override
  protected void onPostExecute(String string) {
    Log.println(Log.INFO,"Post Execute","************************Post Execute******************");
    MainActivity mainActivity = (MainActivity) this.context;
    TextView view = (TextView)mainActivity.findViewById(R.id.random_text);
    view.setText(string);
  }

  @Override
  protected void onCancelled(String string) {
    Log.println(Log.INFO,"Cancelled Executed","*********Cancelled Executed*******" + string);
  }
}
