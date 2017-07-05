package raghav.akash.popularmovies.network;

import raghav.akash.popularmovies.BuildConfig;
import raghav.akash.popularmovies.util.Constants;

/**
 * Created on 22/5/17.
 *
 * @author raghav
 */

public class UrlGenerator {

  private static final String YOUTUBE_VIEW_URL = "http://www.youtube.com/watch?v=";

  public static String getMoviesListUrl(String sortOrder) {
    return BuildConfig.BASE_URL + Constants.URL_PATH_SEPARATOR + sortOrder;
  }

  public static String getMoviePosterUrl(String thumbnailPath) {
    return BuildConfig.BASE_POSTER_URL_185 + Constants.URL_PATH_SEPARATOR + thumbnailPath;
  }

  public static String getAllTrailersUrl(String movieId) {
    return BuildConfig.BASE_URL + Constants.URL_PATH_SEPARATOR + movieId + Constants.URL_PATH_SEPARATOR + Constants.VIDEOS;
  }

  public static String getAllReviewsUrl(String movieId) {
    return BuildConfig.BASE_URL + Constants.URL_PATH_SEPARATOR + movieId + Constants.URL_PATH_SEPARATOR + Constants.REVIEWS;
  }

  public static String getYoutubeTrailerURL(String key) {
    return YOUTUBE_VIEW_URL + key;
  }
}
