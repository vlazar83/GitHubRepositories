package com.example.i060663.githubrepositories;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class RepositoryDetailsActivity extends AppCompatActivity {

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

        ArrayList<Contributor> contributors = QueryUtils.extractContributors(QueryUtils.SAMPLE_JSON_RESPONSE);

        ContributorAdapter adapter = new ContributorAdapter(this, contributors);

        ListView listView = (ListView) findViewById(R.id.contributors_list);

        listView.setAdapter(adapter);


    }
}
