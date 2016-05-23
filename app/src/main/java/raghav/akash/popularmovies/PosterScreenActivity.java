package raghav.akash.popularmovies;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.GridView;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

public class PosterScreenActivity extends AppCompatActivity {

  private static final String LOG_TAG = "Popular Movies";
  private Toolbar toolbar;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_poster_screen);
    toolbar = (Toolbar) findViewById(R.id.toolbar);
    if (toolbar != null) {
      toolbar.setTitle(R.string.top_rated_str);
    }
    setSupportActionBar(toolbar);
    getMoviePosters();
  }

  private void setMoviePosters(String jsonString) {
    try {
      JSONArray jsonArray = new JSONArray(jsonString);
      ArrayList<String> imageList = new ArrayList<>();
      for (int i = 0; i < jsonArray.length(); i++) {
        imageList.add(jsonArray.getJSONObject(i).getString(""));
      }
      GridView gridView = (GridView) findViewById(R.id.content_poster_screen_grid_view);
      if (gridView != null) {
        gridView.setAdapter(new ImageAdapter(this, imageList));
      }
    } catch (JSONException e) {
      Log.e(LOG_TAG, e.getMessage());
    }
  }

  private void getMoviePosters() {
    new AsyncTask<String, Void, String>() {
      @Override
      protected String doInBackground(String... params) {
        HttpURLConnection urlConnection = null;
        StringBuilder stringBuilder = new StringBuilder();
        try {
          URL url = new URL("");
          urlConnection = (HttpURLConnection) url.openConnection();
          InputStream inputStream = urlConnection.getInputStream();
          Scanner scanner = new Scanner(inputStream);
          String data;
          while ((data = scanner.nextLine()) != null) {
            stringBuilder.append(data);
          }
        } catch (java.io.IOException e) {
          Log.e(LOG_TAG, e.getMessage());
        } finally {
          if (urlConnection != null) {
            urlConnection.disconnect();
          }
        }
        return stringBuilder.toString();
      }

      @Override
      protected void onPostExecute(String s) {
        setMoviePosters(s);
      }
    }.execute();
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.poster_screen_menu, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    if (item.getItemId() == R.id.menu_top_rated) {
      toolbar.setTitle(R.string.top_rated_str);
    } else {
      toolbar.setTitle(R.string.most_popular_str);
    }
    return true;
  }
}
