package raghav.akash.popularmovies.network;

/**
 * Created on 26/5/17.
 *
 * @author raghav
 */

public interface ResponseCallback {

  void onSuccess(Response response);

  void onFailure(Response response);

}
