package ru.geekbrains.notes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Configuration;
import android.os.Bundle;

public class NoteBodyPortActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_body);
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            finish();
            return;
        }
        if (savedInstanceState == null) {
            NoteBodyFragment coatOfArmsFragment = new NoteBodyFragment();
            coatOfArmsFragment.setArguments(getIntent().getExtras());
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, coatOfArmsFragment).commit();
        }
    }
}