package raghav.akash.popularmovies;

import android.os.AsyncTask;
import android.os.Bundle;
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

public class PosterScreenActivity extends AppCompatActivity {

  private static final String POPULAR_MOVIES = "popular";
  private static final String TOP_MOVIES = "top_rated";
  private static final String LOG_TAG = "Popular Movies";
  private Toolbar toolbar;
  private String posterSortType;
  private RecyclerView PosterGridRecyclerView;
  private ImageAdapter adapter;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_poster_screen);
    init();
    getMoviePosters(POPULAR_MOVIES);
  }

  private void init() {
    toolbar = (Toolbar) findViewById(R.id.toolbar);
    if (toolbar != null) {
      toolbar.setTitle(R.string.most_popular_str);
    }
    setSupportActionBar(toolbar);
    PosterGridRecyclerView = (RecyclerView) findViewById(R.id.content_poster_screen_grid_view);
    PosterGridRecyclerView.setLayoutManager(new GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false));
  }

  private void getMoviePosters(String posterSortType) {
    this.posterSortType = posterSortType;
    AsyncTask<String, Void, String> task = new AsyncTask<String, Void, String>() {
      @Override
      protected String doInBackground(String... params) {
        HttpURLConnection urlConnection = null;
        StringBuilder stringBuilder = new StringBuilder();
        try {
          URL url = new URL(String.format(getString(R.string.movie_base_url), params[0], getString(R.string.api_key)));
          urlConnection = (HttpURLConnection) url.openConnection();
          InputStream inputStream = urlConnection.getInputStream();
          Scanner scanner = new Scanner(inputStream);
          while (scanner.hasNext()) {
            stringBuilder.append(scanner.next());
          }
        } catch (IOException e) {
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
    };
    task.execute(posterSortType);
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
        PosterGridRecyclerView.setAdapter(adapter);
      } else {
        adapter.updateList(movieDetailsList);
      }
    } catch (JSONException e) {
      Log.e(LOG_TAG, e.getMessage());
    }
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.poster_screen_menu, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    if (item.getItemId() == R.id.menu_top_rated) {
      if (posterSortType.equals(POPULAR_MOVIES)) {
        toolbar.setTitle(R.string.top_rated_str);
        getMoviePosters(TOP_MOVIES);
      }
    } else {
      if (posterSortType.equals(TOP_MOVIES)) {
        toolbar.setTitle(R.string.most_popular_str);
        getMoviePosters(POPULAR_MOVIES);
      }
    }
    return true;
  }
}
