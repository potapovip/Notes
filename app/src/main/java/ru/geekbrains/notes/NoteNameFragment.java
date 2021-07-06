package ru.geekbrains.notes;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;


public class NoteNameFragment extends Fragment {

    private boolean isLandscape;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        isLandscape = getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE;
        if (isLandscape) {
            showLandCoatOfArms(0);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_note_name, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        createTextViewList((LinearLayout) view);
    }

    private void createTextViewList(LinearLayout linearLayout) {
        String[] cities = getResources().getStringArray(R.array.names);
        for (int i = 0; i < cities.length; i++) {
            TextView textView = new TextView(getContext());
            textView.setText(cities[i]);
            textView.setTextSize(30);
            final int FINAL_I = i;
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (isLandscape) {
                        showLandCoatOfArms(FINAL_I);
                    } else {
                        showPortCoatOfArms(FINAL_I);
                    }

                }
            });
            linearLayout.addView(textView);
        }
    }

    private void showPortCoatOfArms(int finalI) {
        Intent intent = new Intent(getActivity(), NoteBodyPortActivity.class);
        intent.putExtra(NoteBodyFragment.KEY_INDEX, finalI);
        startActivity(intent);
    }

    private void showLandCoatOfArms(int index) {
        NoteBodyFragment coatOfArmsFragment = NoteBodyFragment.newInstance(index);
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.name_of_note_land_container, coatOfArmsFragment).commit();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }


}