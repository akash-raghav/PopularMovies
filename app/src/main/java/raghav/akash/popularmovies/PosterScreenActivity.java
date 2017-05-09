package raghav.akash.popularmovies;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

import raghav.akash.popularmovies.adapter.ImageAdapter;
import raghav.akash.popularmovies.model.MovieDetails;

public class PosterScreenActivity extends AppCompatActivity {

  private static final String TAG = "Popular Movies";
  //  private static final int MOVIE_LIST_TYPE = 100;
  private Toolbar toolbar;
  private RecyclerView posterGridRecyclerView;
  private ImageAdapter adapter;
  private SharedPreferences sharedPreferences;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_poster_screen);
    sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
    init();
  }

  @Override
  protected void onStart() {
    super.onStart();
    getMoviePosters(sharedPreferences.getString(getString(R.string.pref_sortOrderKey), getString(R.string.pref_defaultMovieSortOrder)));
  }

  private void init() {
    toolbar = (Toolbar) findViewById(R.id.toolbar);
    if (toolbar != null) {
      toolbar.setTitle(R.string.title_activity_poster_screen);
    }
    setSupportActionBar(toolbar);
    posterGridRecyclerView = (RecyclerView) findViewById(R.id.content_poster_screen_grid_view);
  }

  private void getMoviePosters(String moviesListType) {
    Log.d(TAG, "getMoviePosters: " + moviesListType);
    AsyncTask<String, Void, String> task = new AsyncTask<String, Void, String>() {
      @Override
      protected String doInBackground(String... params) {
        HttpURLConnection urlConnection = null;
        StringBuilder stringBuilder = new StringBuilder();
        try {
          URL url = new URL(String.format(getString(R.string.movie_base_url), params[0], getString(R.string.api_key)));
          Log.d("Popular Movies", url.getPath());
          urlConnection = (HttpURLConnection) url.openConnection();
          InputStream inputStream = urlConnection.getInputStream();
          Scanner scanner = new Scanner(inputStream);
          while (scanner.hasNext()) {
            stringBuilder.append(scanner.next());
          }
        } catch (IOException e) {
          Log.e(TAG, e.getMessage());
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
    };
    task.execute(moviesListType);
  }

  private void setMoviePosters(String jsonString) {
    try {
      JSONObject resultObject = new JSONObject(jsonString);
      JSONArray jsonArray = resultObject.getJSONArray("results");
      ArrayList<MovieDetails> movieDetailsList = new ArrayList<>();
      for (int i = 0; i < jsonArray.length(); i++) {
        movieDetailsList.add(MovieDetails.parseDetails(jsonArray.getJSONObject(i)));
      }
      if (adapter == null) {
        adapter = new ImageAdapter(this, movieDetailsList);
        posterGridRecyclerView.setLayoutManager(new GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false));
        posterGridRecyclerView.setAdapter(adapter);
      } else {
        adapter.updateList(movieDetailsList);
      }
    } catch (JSONException e) {
      Log.e(TAG, e.getMessage());
    }
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.settings_menu, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    if (item.getItemId() == R.id.settings) {
      startActivity(new Intent(this, SettingsActivity.class));
      return true;
    }
    return super.onOptionsItemSelected(item);
  }
}
