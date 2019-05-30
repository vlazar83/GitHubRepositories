package com.example.i060663.githubrepositories;

import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.app.LoaderManager;

import java.util.ArrayList;
import java.util.List;

public class RepositoryDetailsActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<Contributor>>{

    private static final String LOG_TAG = RepositoryDetailsActivity.class.getName();

    private static final int CONTRIBUTORS_LOADER_ID = 2;

    private static final String GITHUB_REQUEST_URL_FOR_CONTRIBUTORS =
            "https://api.github.com/repos/kpu/kenlm/contributors";

    private ContributorAdapter contributorAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_repositoiry_details);

        Intent intent = getIntent();

        Repository repositoryObject = (Repository) intent.getSerializableExtra(GitHubSearchActivity.INTENT_REPOSITORY_DETAILS);

        TextView sizeTextView = findViewById(R.id.size);
        sizeTextView.setText(Integer.toString(repositoryObject.getSize()));

        TextView forkTextView = findViewById(R.id.fork_count);
        forkTextView.setText(Integer.toString(repositoryObject.getForksCount()));

        TextView starGazersTextView = findViewById(R.id.stargazers);
        starGazersTextView.setText(Integer.toString(repositoryObject.getStargazersCount()));

        contributorAdapter = new ContributorAdapter(this, new ArrayList<Contributor>());

        ListView listView = (ListView) findViewById(R.id.contributors_list);

        listView.setAdapter(contributorAdapter);


        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnected()) {

            LoaderManager loaderManager = getLoaderManager();

            loaderManager.initLoader(CONTRIBUTORS_LOADER_ID, null, this);
        } else {

            //View loadingIndicator = findViewById(R.id.loading_indicator);
            //loadingIndicator.setVisibility(View.GONE);

            //emptyStateTextView.setText(R.string.no_internet_connection);
        }


        ImageView avatarImageView = (ImageView) findViewById(R.id.avatarPicture);



    }

    @Override
    public Loader<List<Contributor>> onCreateLoader(int id, Bundle args) {
        return new ContributorLoader(this, GITHUB_REQUEST_URL_FOR_CONTRIBUTORS);
    }

    @Override
    public void onLoadFinished(Loader<List<Contributor>> loader, List<Contributor> contributors) {
        //View loadingIndicator = findViewById(R.id.loading_indicator);
        //loadingIndicator.setVisibility(View.GONE);



        contributorAdapter.clear();

        if (contributors != null && !contributors.isEmpty()) {
            contributorAdapter.addAll(contributors);
        } else {
            //emptyStateTextView.setText(R.string.no_repositories);
        }

    }

    @Override
    public void onLoaderReset(Loader<List<Contributor>> loader) {
        contributorAdapter.clear();
    }
}
