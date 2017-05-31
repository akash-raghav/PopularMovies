package raghav.akash.popularmovies;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import butterknife.InjectView;
import raghav.akash.popularmovies.model.MovieDetails;
import raghav.akash.popularmovies.network.UrlGenerator;
import raghav.akash.popularmovies.network.ApiRequestGenerator;
import raghav.akash.popularmovies.network.Response;
import raghav.akash.popularmovies.network.ResponseCallback;
import raghav.akash.popularmovies.util.CustomLog;

public class DetailsActivity extends ToolbarActivity {

  public static final String MOVIE_DATA = "movie_data";
  @InjectView(R.id.movie_title)
  TextView titleTxt;
  @InjectView(R.id.movie_overview)
  TextView overviewTxt;
  @InjectView(R.id.movie_rating)
  TextView ratingTxt;
  @InjectView(R.id.movie_release_date)
  TextView releaseDateTxt;
  @InjectView(R.id.movie_poster_thumb)
  ImageView postImg;
  @InjectView(R.id.trailers_recycler_view)
  RecyclerView trailersRecyclerView;
  private MovieDetails movieDetails;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.content_details);
    setupDetailViews();
    ApiRequestGenerator.getTrailersList(this, movieDetails.getId(), new ResponseCallback() {
      @Override
      public void onSuccess(Response response) {
        setupTrailerRecycler(response.getData());
      }

      @Override
      public void onFailure(Response response) {
        CustomLog.e(response.getMessage());
      }
    });
  }

  @Override
  protected void onRestoreInstanceState(Bundle savedInstanceState) {
    super.onRestoreInstanceState(savedInstanceState);
  }

  private void setupDetailViews() {
    movieDetails = getIntent().getParcelableExtra(MOVIE_DATA);
    titleTxt.setText(movieDetails.getTitle());
    ratingTxt.setText(movieDetails.getRating());
    releaseDateTxt.setText(movieDetails.getReleaseDate());
    overviewTxt.setText(movieDetails.getOverview());
    Picasso.with(this)
        .load(UrlGenerator.getMoviePosterUrl(movieDetails.getImageThumbnail()))
        .placeholder(R.drawable.place_holder)
        .error(R.drawable.place_holder)
        .into(postImg);
  }

  private void setupTrailerRecycler(String s) {
  }

  @Override
  protected void onSaveInstanceState(Bundle outState) {
    super.onSaveInstanceState(outState);
  }
}
