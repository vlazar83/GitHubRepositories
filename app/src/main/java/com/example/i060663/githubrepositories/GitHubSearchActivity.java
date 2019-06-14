package com.example.i060663.githubrepositories;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class GitHubSearchActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<Repository>> {

    private static final String LOG_TAG = GitHubSearchActivity.class.getName();
    private static int currentPage = 1;

    // private static String GITHUB_REQUEST_URL = "https://api.github.com/search/repositories?q={query}&per_page=25&page=";
    private static String GITHUB_REQUEST_URL = "https://api.github.com/search/repositories?q={query}";

    public static final String INTENT_REPOSITORY_DETAILS = "INTENT_REPOSITORY_DETAILS";

    private static final int REPOSITORY_LOADER_ID = 1;

    private RepositoryAdapter repositoryAdapter;

    private TextView emptyStateTextView;

    private static int maxPage = 40;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_git_hub_search);

        ListView listView = (ListView) findViewById(R.id.list);
        emptyStateTextView = (TextView) findViewById(R.id.empty_view);
        listView.setEmptyView(emptyStateTextView);

        repositoryAdapter = new RepositoryAdapter(this, new ArrayList<Repository>());

        listView.setAdapter(repositoryAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent repositoryDetailsIntent = new Intent(GitHubSearchActivity.this, RepositoryDetailsActivity.class);
                repositoryDetailsIntent.putExtra(INTENT_REPOSITORY_DETAILS,repositoryAdapter.getItem(position));

                // Start the new activity
                startActivity(repositoryDetailsIntent);

            }
        });

        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnected()) {

            LoaderManager loaderManager = getLoaderManager();

            loaderManager.initLoader(REPOSITORY_LOADER_ID, null, this);
        } else {

            View loadingIndicator = findViewById(R.id.loading_indicator);
            loadingIndicator.setVisibility(View.GONE);

            emptyStateTextView.setText(R.string.no_internet_connection);
        }

        final FloatingActionButton prevButton = (FloatingActionButton) findViewById(R.id.fab_prev);
        final FloatingActionButton nextButton = (FloatingActionButton) findViewById(R.id.fab_next);

        prevButton.setEnabled(false);
        prevButton.setAlpha(0.5f);
        nextButton.setAlpha(1.0f);
        prevButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                View loadingIndicator = findViewById(R.id.loading_indicator);
                loadingIndicator.setVisibility(View.VISIBLE);
                LoaderManager loaderManager = getLoaderManager();
                currentPage -= 1;
                if(currentPage == 1){
                    prevButton.setEnabled(false);
                    prevButton.setAlpha(0.5f);
                    nextButton.setAlpha(1.0f);
                } else {
                    prevButton.setEnabled(true);
                    prevButton.setAlpha(1.0f);
                }
                loaderManager.restartLoader(REPOSITORY_LOADER_ID, null, GitHubSearchActivity.this);

            }
        });

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                View loadingIndicator = findViewById(R.id.loading_indicator);
                loadingIndicator.setVisibility(View.VISIBLE);
                LoaderManager loaderManager = getLoaderManager();
                currentPage += 1;

                if(currentPage == maxPage){
                    nextButton.setEnabled(false);
                    nextButton.setAlpha(0.5f);
                } else {
                    nextButton.setEnabled(true);
                    nextButton.setAlpha(1.0f);
                }

                if(currentPage > 1){
                    prevButton.setEnabled(true);
                    prevButton.setAlpha(1.0f);
                } else if(currentPage == 1){
                    prevButton.setEnabled(false);
                    prevButton.setAlpha(0.5f);
                }

                loaderManager.restartLoader(REPOSITORY_LOADER_ID, null, GitHubSearchActivity.this);

            }
        });

    }

    @Override
    public Loader<List<Repository>> onCreateLoader(int i, Bundle bundle) {

        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this);
        String perPage = sharedPrefs.getString(
                getString(R.string.settings_per_page_key),
                getString(R.string.settings_per_page_default));

        Uri baseUri = Uri.parse(GITHUB_REQUEST_URL);
        Uri.Builder uriBuilder = baseUri.buildUpon();

        uriBuilder.appendQueryParameter("per_page", perPage);
        uriBuilder.appendQueryParameter("page", String.valueOf(currentPage));

        return new RepositoryLoader(this, uriBuilder.toString());
    }

    @Override
    public void onLoadFinished(Loader<List<Repository>> loader, List<Repository> repositories) {

        View loadingIndicator = findViewById(R.id.loading_indicator);
        loadingIndicator.setVisibility(View.GONE);



        repositoryAdapter.clear();

        if (repositories != null && !repositories.isEmpty()) {
            repositoryAdapter.addAll(repositories);
        } else {
            emptyStateTextView.setText(R.string.no_repositories);
        }
    }

    @Override
    public void onLoaderReset(Loader<List<Repository>> loader) {
        // Loader reset, so we can clear out our existing data.
        repositoryAdapter.clear();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            Intent settingsIntent = new Intent(this, SettingsActivity.class);
            startActivity(settingsIntent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
