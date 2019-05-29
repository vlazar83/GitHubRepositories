package com.example.i060663.githubrepositories;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class RepositoryDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_repositoiry_details);

        Intent intent = getIntent();

        Repository repositoryObject = (Repository) intent.getSerializableExtra(GitHubSearchActivity.INTENT_REPOSITORY_DETAILS);

        TextView detailsView = findViewById(R.id.repositoryDetailsTextView);
        detailsView.setText(repositoryObject.getFullName());



    }
}
