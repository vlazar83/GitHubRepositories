package com.example.i060663.githubrepositories;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_activity);
    }

    public static class GitHubPreferenceFragment extends PreferenceFragment implements Preference.OnPreferenceChangeListener {
        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.settings_main);

            Preference perPage = findPreference(getString(R.string.settings_per_page_key));
            bindPreferenceSummaryToValue(perPage);

        }

        private void bindPreferenceSummaryToValue(Preference preference) {
            preference.setOnPreferenceChangeListener(this);
            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(preference.getContext());
            String preferenceString = preferences.getString(preference.getKey(), "");
            onPreferenceChange(preference, preferenceString);
        }

        @Override
        public boolean onPreferenceChange(Preference preference, Object newValue) {
            String stringValue = newValue.toString();
            int val = Integer.parseInt(newValue.toString());

            if ((val <= 30) && (val >= 5)) {
                Log.d("Preference ","Value saved: " + val);
                preference.setSummary(stringValue);
                return true;
            }
            else {
                Toast.makeText(getActivity(), "Choose something between 5 and 30", Toast.LENGTH_LONG).show();
                return false;
            }

        }
    }
}
