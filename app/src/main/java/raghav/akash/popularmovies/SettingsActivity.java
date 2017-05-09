package raghav.akash.popularmovies;

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
    getFragmentManager().beginTransaction().replace(android.R.id.content, new MyPreferenceFragment()).commit();
  }

  public static class MyPreferenceFragment extends PreferenceFragment implements Preference.OnPreferenceChangeListener {

    @Override
    public void onCreate(final Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      addPreferencesFromResource(R.xml.preferences);
      bindPreferenceSummaryToValue(findPreference(getString(R.string.pref_sortOrderKey)));
    }

    private void bindPreferenceSummaryToValue(Preference preference) {
      preference.setOnPreferenceChangeListener(this);
      onPreferenceChange(preference,
          PreferenceManager.getDefaultSharedPreferences(preference.getContext())
              .getString(preference.getKey(), getString(R.string.pref_defaultMovieSortOrder)));
    }

    @Override
    public boolean onPreferenceChange(Preference preference, Object o) {
      if (preference instanceof ListPreference) {
        ListPreference pref = (ListPreference) preference;
        pref.setValue(String.valueOf(o));
        pref.setSummary(pref.getEntry());
        return true;
      }
      return false;
    }
  }
}
