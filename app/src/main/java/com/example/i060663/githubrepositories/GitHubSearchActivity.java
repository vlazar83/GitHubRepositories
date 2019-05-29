package com.example.i060663.githubrepositories;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
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

    private static String GITHUB_REQUEST_URL =
            //"https://api.github.com/search/repositories?q={query}&{page,per_page,sort,order}";
            "https://api.github.com/search/repositories?q={query}&per_page=25&page=";

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


        final Button nextButton = (Button) findViewById(R.id.next);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                View loadingIndicator = findViewById(R.id.loading_indicator);
                loadingIndicator.setVisibility(View.VISIBLE);
                LoaderManager loaderManager = getLoaderManager();
                currentPage += 1;

                if(currentPage == maxPage){
                    nextButton.setEnabled(false);
                } else {
                    nextButton.setEnabled(true);
                }

                loaderManager.restartLoader(REPOSITORY_LOADER_ID, null, GitHubSearchActivity.this);

            }
        });

        final Button prevButton = (Button) findViewById(R.id.prev);
        prevButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                View loadingIndicator = findViewById(R.id.loading_indicator);
                loadingIndicator.setVisibility(View.VISIBLE);
                LoaderManager loaderManager = getLoaderManager();
                currentPage -= 1;
                if(currentPage == 1){
                    prevButton.setEnabled(false);
                } else {
                    prevButton.setEnabled(true);
                }
                loaderManager.restartLoader(REPOSITORY_LOADER_ID, null, GitHubSearchActivity.this);

            }
        });

    }

    @Override
    public Loader<List<Repository>> onCreateLoader(int i, Bundle bundle) {

        return new RepositoryLoader(this, GITHUB_REQUEST_URL + currentPage);
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

}
