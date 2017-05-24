package raghav.akash.popularmovies.network;

import java.util.HashMap;
import java.util.Map;

import raghav.akash.popularmovies.BuildConfig;
import raghav.akash.popularmovies.util.Constants;

/**
 * Created on 22/5/17.
 *
 * @author raghav
 */

public class ApiUrl {

  public static String getMoviesListUrl(String sortOrder, String apiKey) {
    String url = BuildConfig.BASE_URL + sortOrder;
    Map<String, String> map = new HashMap<>();
    map.put(Constants.API_KEY, apiKey);
    return addParams(url, map);
  }

  public static String getMoviePosterUrl(String thumbnailPath) {
    return BuildConfig.BASE_POSTER_URL_185 + thumbnailPath;
  }

  private static String addParams(String urlString, Map<String, String> params) {
    StringBuilder stringBuilder = new StringBuilder(urlString);
    boolean flag = true;
    if (!urlString.contains(Constants.URL_QUERY_SEPARATOR)) {
      stringBuilder.append(Constants.URL_QUERY_SEPARATOR);
      flag = false;
    }
    for (Map.Entry<String, String> entry : params.entrySet()) {
      if (flag) {
        stringBuilder.append(Constants.URL_PARAM_SEPARATOR);
      }
      flag = false;
      stringBuilder.append(entry.getKey())
          .append(Constants.URL_KEY_VALUE_SEPARATOR)
          .append(entry.getValue());
    }
    return stringBuilder.toString();
  }
}
