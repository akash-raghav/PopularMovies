package raghav.akash.popularmovies.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import raghav.akash.popularmovies.R;

public class TrailerFragment extends Fragment {

  public TrailerFragment() {
    // Required empty public constructor
  }

  /**
   * Use this factory method to create a new instance of
   * this fragment using the provided parameters.
   *
   * @return A new instance of fragment TrailerFragment.
   */
  public static TrailerFragment newInstance(Bundle args) {
    TrailerFragment fragment = new TrailerFragment();
    fragment.setArguments(args);
    return fragment;
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    return inflater.inflate(R.layout.fragment_trailer, container, false);
  }

}
