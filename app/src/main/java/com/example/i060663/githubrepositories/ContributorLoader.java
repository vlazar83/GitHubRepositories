package com.example.i060663.githubrepositories;

import android.content.AsyncTaskLoader;
import android.content.Context;

import java.util.List;

public class ContributorLoader extends AsyncTaskLoader<List<Contributor>> {

    /** Tag for log messages */
    private static final String LOG_TAG = ContributorLoader.class.getName();

    /** Query URL */
    private String queryUrl;

    public ContributorLoader(Context context, String url) {
        super(context);
        queryUrl = url;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    public List<Contributor> loadInBackground() {
        if (queryUrl == null) {
            return null;
            }

        // Perform the network request, parse the response, and extract a list of repositories.
        List<Contributor> contributors = QueryUtils.fetchContributorData(queryUrl);
        return contributors;
    }
}
