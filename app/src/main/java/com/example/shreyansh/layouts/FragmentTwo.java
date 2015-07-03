package com.example.shreyansh.layouts;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

public class FragmentTwo extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.table_layout, container, false);
        if (savedInstanceState != null) {
            String firstName = savedInstanceState.getString("firstName", "");
            ((TextView) view.findViewById(R.id.first_name)).setText(firstName);
            String lastName = savedInstanceState.getString("lastName", "");
            ((TextView) view.findViewById(R.id.last_name)).setText(lastName);
            int stars = savedInstanceState.getInt("stars", 0);
            ((RatingBar) view.findViewById(R.id.rating_bar)).setNumStars(stars);
        }
        return view;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        View view = getView();
        if (view != null) {
            String firstName = ((TextView) view.findViewById(R.id.first_name)).getText().toString();
            outState.putString("firstName", firstName);
            String lastName = ((TextView) view.findViewById(R.id.last_name)).getText().toString();
            outState.putString("lastName", lastName);
            int stars = ((RatingBar) view.findViewById(R.id.rating_bar)).getNumStars();
            outState.putInt("stars", stars);
        } else {
            Logger.log("got null view");
        }
        super.onSaveInstanceState(outState);
    }
}
