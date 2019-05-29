package com.example.i060663.githubrepositories;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class RepositoryAdapter extends ArrayAdapter<Repository> {

    public RepositoryAdapter(Activity context, ArrayList<Repository> repositories) {

        super(context, 0, repositories);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        // Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }

        Repository currentRepository = getItem(position);

        TextView repositoryTextView = (TextView) listItemView.findViewById(R.id.repo_name);

        repositoryTextView.setText(currentRepository.getName());

        TextView fullNameTextView = (TextView) listItemView.findViewById(R.id.repo_full_name);

        fullNameTextView.setText(currentRepository.getFullName());

        return listItemView;


    }
}
