package raghav.akash.popularmovies;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import butterknife.InjectView;
import raghav.akash.popularmovies.adapter.DetailsAdapter;
import raghav.akash.popularmovies.model.MovieDetails;
import raghav.akash.popularmovies.network.ApiRequestGenerator;
import raghav.akash.popularmovies.network.Response;
import raghav.akash.popularmovies.network.ResponseCallback;
import raghav.akash.popularmovies.util.Constants;
import raghav.akash.popularmovies.util.CustomLog;

public class MoviesActivity extends ToolbarActivity {

  @InjectView(R.id.content_poster_screen_grid_view)
  RecyclerView posterGridRecyclerView;
  private DetailsAdapter adapter;
  private SharedPreferences sharedPreferences;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.content_movies);
    sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
  }

  @Override
  protected void onStart() {
    super.onStart();
    getMoviePosters(sharedPreferences.getString(getString(R.string.pref_sortOrderKey), getString(R.string.pref_defaultMovieSortOrder)));
  }

  private void getMoviePosters(String moviesListType) {
    CustomLog.d("getMoviePosters: " + moviesListType);
    ApiRequestGenerator.getMoviesList(this, moviesListType, new ResponseCallback() {
      @Override
      public void onSuccess(Response response) {
        setMoviePosters(response.getData());
      }

      @Override
      public void onFailure(Response response) {
        CustomLog.e(response.getMessage());
      }
    });
  }

  private void setMoviePosters(String jsonString) {
    try {
      JSONObject resultObject = new JSONObject(jsonString);
      JSONArray jsonArray = resultObject.getJSONArray(Constants.RESULTS);
      ArrayList<MovieDetails> movieDetailsList = new ArrayList<>();
      for (int i = 0; i < jsonArray.length(); i++) {
        movieDetailsList.add(MovieDetails.parseDetails(jsonArray.getJSONObject(i)));
      }
      if (adapter == null) {
        adapter = new DetailsAdapter(this, movieDetailsList);
        posterGridRecyclerView.setLayoutManager(new GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false));
        posterGridRecyclerView.setAdapter(adapter);
      } else {
        adapter.updateList(movieDetailsList);
      }
    } catch (JSONException e) {
      CustomLog.e(e.getMessage());
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
