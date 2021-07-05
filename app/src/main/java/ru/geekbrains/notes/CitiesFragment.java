package ru.geekbrains.notes;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;


public class CitiesFragment extends Fragment {




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_cities, container, false);
    }

    @Override
    public void onViewCreated( View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        createTextViewList((LinearLayout) view);
    }

    private void createTextViewList(LinearLayout linearLayout) {
        String [] cities = getResources().getStringArray(R.array.cities);
        for (int i = 0; i < cities.length; i++) {
            TextView textView = new TextView(getContext());
            textView.setText(cities[i]);
            textView.setTextSize(35);
            linearLayout.addView(textView);
        }
    }

    public static CitiesFragment newInstance(String param1, String param2) {
        CitiesFragment fragment = new CitiesFragment();
        Bundle args = new Bundle();
        //args.putString(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }


}