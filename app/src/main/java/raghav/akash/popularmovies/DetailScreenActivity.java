package raghav.akash.popularmovies;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import raghav.akash.popularmovies.model.MovieDetails;

public class DetailScreenActivity extends AppCompatActivity {

  public static final String MOVIE_DATA = "movie_data";
  private TextView titleTxt;
  private TextView overviewTxt;
  private TextView ratingTxt;
  private TextView releaseDateTxt;
  private ImageView postImg;
  private MovieDetails movieDetails;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_detail_screen);
    init();
    setupViews();
  }

  private void init() {
    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);
    titleTxt = (TextView) findViewById(R.id.movie_title);
    overviewTxt = (TextView) findViewById(R.id.movie_overview);
    ratingTxt = (TextView) findViewById(R.id.movie_rating);
    releaseDateTxt = (TextView) findViewById(R.id.movie_release_date);
    postImg = (ImageView) findViewById(R.id.movie_poster_thumb);
    movieDetails = getIntent().getParcelableExtra(MOVIE_DATA);
  }

  private void setupViews() {
    titleTxt.setText(movieDetails.getTitle());
    ratingTxt.setText(movieDetails.getRating());
    releaseDateTxt.setText(movieDetails.getReleaseDate());
    overviewTxt.setText(movieDetails.getOverview());
    Picasso.with(this)
        .load(getString(R.string.base_image_url_185) + movieDetails.getImageThumbnail())
        .into(postImg);
  }

}
