package raghav.akash.popularmovies;

import android.app.Application;

import raghav.akash.popularmovies.network.NetworkRequestHandler;

/**
 * Created on 21/6/17.
 *
 * @author raghav
 */

public class BaseApplication extends Application {

  @Override
  public void onCreate() {
    super.onCreate();
    NetworkRequestHandler.setupNetworkHandler(this);
  }
}
