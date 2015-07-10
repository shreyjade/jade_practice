package com.example.shreyansh.layouts;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

public class FragmentTwo extends Fragment {

    private String mFirstName = "First Name";
    private String mLastName = "Last Name";
    private String mStars = "Stars";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.table_layout, container, false);
        if (savedInstanceState != null) {
            String firstName = savedInstanceState.getString(mFirstName, "");
            ((TextView) view.findViewById(R.id.first_name)).setText(firstName);
            String lastName = savedInstanceState.getString(mLastName, "");
            ((TextView) view.findViewById(R.id.last_name)).setText(lastName);
            int stars = savedInstanceState.getInt(mStars, 0);
            ((RatingBar) view.findViewById(R.id.rating_bar)).setNumStars(stars);
        }
        return view;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        View view = getView();
        if (view != null) {
            String firstName = ((TextView) view.findViewById(R.id.first_name)).getText().toString();
            outState.putString(mFirstName, firstName);
            String lastName = ((TextView) view.findViewById(R.id.last_name)).getText().toString();
            outState.putString(mLastName, lastName);
            int stars = ((RatingBar) view.findViewById(R.id.rating_bar)).getNumStars();
            outState.putInt(mStars, stars);
        } else {
            Logger.log("got null view");
        }
        super.onSaveInstanceState(outState);
    }
}
