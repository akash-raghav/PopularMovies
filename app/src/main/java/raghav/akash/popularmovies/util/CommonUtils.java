package raghav.akash.popularmovies.util;

import android.content.Context;
import android.net.ConnectivityManager;

/**
 * Created on 29/5/17.
 *
 * @author raghav
 */

public class CommonUtils {

  public static boolean isNetworkConnected(Context context) {
    ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
    return cm.getActiveNetworkInfo() != null;
  }
}
