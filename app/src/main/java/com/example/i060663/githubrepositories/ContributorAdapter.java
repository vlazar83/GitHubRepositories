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

public class ContributorAdapter extends ArrayAdapter<Contributor> {

    public ContributorAdapter(Activity context, ArrayList<Contributor> contributors) {

        super(context, 0, contributors);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        // Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.repository_details_list_item, parent, false);
        }

        Contributor currentContributor = getItem(position);

        TextView loginNameTextView = (TextView) listItemView.findViewById(R.id.login_name);

        loginNameTextView.setText(currentContributor.getLoginName());

        // image View setup here...

        return listItemView;


    }
}