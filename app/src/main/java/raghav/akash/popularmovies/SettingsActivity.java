package raghav.akash.popularmovies;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;

public class SettingsActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_settings);
    FragmentTransaction transaction = getFragmentManager().beginTransaction();
    transaction.replace(R.id.activity_settings, new SettingsFragment());
    transaction.commit();
  }

  public static class SettingsFragment extends PreferenceFragment implements Preference.OnPreferenceChangeListener {

    public SettingsFragment() {
      // default constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      addPreferencesFromResource(R.xml.preferences);
      bindPreferenceSummaryTovalue(findPreference(getString(R.string.pref_sortOrderKey)));
    }

    private void bindPreferenceSummaryTovalue(Preference preference) {
      preference.setOnPreferenceChangeListener(this);

      onPreferenceChange(preference,
          PreferenceManager.getDefaultSharedPreferences(preference.getContext())
              .getString(preference.getKey(), ""));
    }

    @Override
    public boolean onPreferenceChange(Preference preference, Object o) {
      if (preference instanceof ListPreference) {

        return true;
      }
      return false;
    }
  }
}
