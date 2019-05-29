package com.example.i060663.githubrepositories;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;

public class GitHubSearchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_git_hub_search);

        /*
        ArrayList<Repository> repositories = new ArrayList<>();
        repositories.add(new Repository("one", "egy"));
        repositories.add(new Repository("two", "ketto"));
        repositories.add(new Repository("three", "harom"));
        repositories.add(new Repository("four", "negy"));
        repositories.add(new Repository("five", "ot"));
        repositories.add(new Repository("sic", "hat"));
        repositories.add(new Repository("seven", "het"));
        repositories.add(new Repository("eight", "nyolc"));
        repositories.add(new Repository("nine", "kilenc"));
        repositories.add(new Repository("ten", "tiz"));
        */

        ArrayList<Repository> repositories = QueryUtils.extractEarthquakes();

        RepositoryAdapter adapter = new RepositoryAdapter(this, repositories);

        ListView listView = (ListView) findViewById(R.id.list);

        listView.setAdapter(adapter);
    }
}
