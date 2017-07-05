package raghav.akash.popularmovies.network;

import android.content.Context;

import java.util.HashMap;
import java.util.Map;

import raghav.akash.popularmovies.R;
import raghav.akash.popularmovies.util.Constants;

/**
 * Created on 31/5/17.
 *
 * @author raghav
 */

public class ApiRequestGenerator {

  public static void getMoviesList(Context context, String moviesListType, ResponseCallback responseCallback) {
    Map<String, String> params = new HashMap<>();
    params.put(Constants.API_KEY, context.getString(R.string.api_key));
    NetworkRequestHandler.sendRequest(context, new Request(UrlGenerator.getMoviesListUrl(moviesListType), params, responseCallback));
  }

  public static void getTrailersList(Context context, String movieId, ResponseCallback responseCallback) {
    Map<String, String> params = new HashMap<>();
    params.put(Constants.API_KEY, context.getString(R.string.api_key));
    NetworkRequestHandler.sendRequest(context, new Request(UrlGenerator.getAllTrailersUrl(movieId), params, responseCallback));
  }

  public static void getReviewsList(Context context, String movieId, ResponseCallback responseCallback) {
    Map<String, String> params = new HashMap<>();
    params.put(Constants.API_KEY, context.getString(R.string.api_key));
    NetworkRequestHandler.sendRequest(context, new Request(UrlGenerator.getAllReviewsUrl(movieId), params, responseCallback));
  }
}
