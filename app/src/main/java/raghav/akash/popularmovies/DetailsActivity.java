package raghav.akash.popularmovies;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import butterknife.InjectView;
import raghav.akash.popularmovies.model.MovieDetails;
import raghav.akash.popularmovies.network.ApiUrl;

public class DetailsActivity extends ToolbarActivity {

  public static final String MOVIE_DATA = "movie_data";
  @InjectView(R.id.movie_title) TextView titleTxt;
  @InjectView(R.id.movie_overview) TextView overviewTxt;
  @InjectView(R.id.movie_rating) TextView ratingTxt;
  @InjectView(R.id.movie_release_date) TextView releaseDateTxt;
  @InjectView(R.id.movie_poster_thumb) ImageView postImg;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.content_details);
    setupViews();
  }

  @Override
  protected void onRestoreInstanceState(Bundle savedInstanceState) {
    super.onRestoreInstanceState(savedInstanceState);
  }

  private void setupViews() {
    MovieDetails movieDetails = getIntent().getParcelableExtra(MOVIE_DATA);
    titleTxt.setText(movieDetails.getTitle());
    ratingTxt.setText(movieDetails.getRating());
    releaseDateTxt.setText(movieDetails.getReleaseDate());
    overviewTxt.setText(movieDetails.getOverview());
    Picasso.with(this)
        .load(ApiUrl.getMoviePosterUrl(movieDetails.getImageThumbnail()))
        .into(postImg);
  }

  @Override
  protected void onSaveInstanceState(Bundle outState) {
    super.onSaveInstanceState(outState);
  }
}
