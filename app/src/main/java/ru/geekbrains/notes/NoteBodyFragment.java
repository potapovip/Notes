package ru.geekbrains.notes;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class NoteBodyFragment extends Fragment {

    private int index;
    public static final String KEY_INDEX = "index";


    public static NoteBodyFragment newInstance(int index) {
        NoteBodyFragment fragment = new NoteBodyFragment();
        Bundle args = new Bundle();
        args.putInt(KEY_INDEX, index);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            index = getArguments().getInt(KEY_INDEX);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_note_body, container, false);
        TextView textViewBody = view.findViewById(R.id.body_of_note);
        TextView textViewDate = view.findViewById(R.id.date_of_note);
        String[] s = getResources().getStringArray(R.array.text);
        String[] s2 = getResources().getStringArray(R.array.date);
        textViewBody.setText(s[index]);
        textViewDate.setText(s2[index]);
        return view;
    }
}