package com.example.i060663.githubrepositories;

import android.content.AsyncTaskLoader;
import android.content.Context;

import java.util.List;

public class RepositoryLoader extends AsyncTaskLoader<List<Repository>> {

    private static final String LOG_TAG = RepositoryLoader.class.getName();

    private String queryURL;

    public RepositoryLoader(Context context, String url) {
        super(context);
        queryURL = url;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    public List<Repository> loadInBackground() {
        if (queryURL == null) {
            return null;
        }

        // Perform the network request, parse the response, and extract a list of repositories.
        List<Repository> repositories = QueryUtils.fetchRepositoryData(queryURL);
        return repositories;
    }
}
