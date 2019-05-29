package com.example.i060663.githubrepositories;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class GitHubSearchActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<Repository>> {

    private static final String LOG_TAG = GitHubSearchActivity.class.getName();
    private static final String GITHUB_REQUEST_URL =
            "https://api.github.com/search/repositories?q={query}&{page,per_page,sort,order}";

    private static final int REPOSITORY_LOADER_ID = 1;

    /** Adapter for the list of earthquakes */
    private RepositoryAdapter mAdapter;

    /** TextView that is displayed when the list is empty */
    private TextView mEmptyStateTextView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_git_hub_search);

        ListView listView = (ListView) findViewById(R.id.list);
        mEmptyStateTextView = (TextView) findViewById(R.id.empty_view);
        listView.setEmptyView(mEmptyStateTextView);

        mAdapter = new RepositoryAdapter(this, new ArrayList<Repository>());

        listView.setAdapter(mAdapter);

        // Get a reference to the ConnectivityManager to check state of network connectivity
        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);

        // Get details on the currently active default data network
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        // If there is a network connection, fetch data
        if (networkInfo != null && networkInfo.isConnected()) {
            // Get a reference to the LoaderManager, in order to interact with loaders.
            LoaderManager loaderManager = getLoaderManager();

            // Initialize the loader. Pass in the int ID constant defined above and pass in null for
            // the bundle. Pass in this activity for the LoaderCallbacks parameter (which is valid
            // because this activity implements the LoaderCallbacks interface).
            loaderManager.initLoader(REPOSITORY_LOADER_ID, null, this);
        } else {
            // Otherwise, display error
            // First, hide loading indicator so error message will be visible
            View loadingIndicator = findViewById(R.id.loading_indicator);
            loadingIndicator.setVisibility(View.GONE);

            // Update empty state with no connection error message
            mEmptyStateTextView.setText(R.string.no_internet_connection);
        }

    }

    @Override
    public Loader<List<Repository>> onCreateLoader(int i, Bundle bundle) {
        // Create a new loader for the given URL
        return new RepositoryLoader(this, GITHUB_REQUEST_URL);
    }

    @Override
    public void onLoadFinished(Loader<List<Repository>> loader, List<Repository> repositories) {
        // Hide loading indicator because the data has been loaded
        View loadingIndicator = findViewById(R.id.loading_indicator);
        loadingIndicator.setVisibility(View.GONE);

        mEmptyStateTextView.setText(R.string.no_repositories);

        // Clear the adapter of previous earthquake data
        //mAdapter.clear();

        // If there is a valid list of {@link Earthquake}s, then add them to the adapter's
        // data set. This will trigger the ListView to update.
        if (repositories != null && !repositories.isEmpty()) {
            mAdapter.addAll(repositories);
        }
    }

    @Override
    public void onLoaderReset(Loader<List<Repository>> loader) {
        // Loader reset, so we can clear out our existing data.
        mAdapter.clear();
    }
}
