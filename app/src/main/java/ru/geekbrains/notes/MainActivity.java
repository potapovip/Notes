package ru.geekbrains.notes;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity implements ParamManager {

    public static final String CURRENT_NOTE = "CurrentNote";
    private static final String NOTE_NAME_TAG = "NoteNameFragment";
    private static final String NOTE_BODY_TAG = "NoteBodyFragment";
    public Note currentNote;
    private int parameter = 0;

    @Override
    public void setParam(int param) {
        parameter = param;
    }

    @Override
    public int getParam() {
        return parameter;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();

        if (savedInstanceState != null) {
            // Восстановление текущей позиции.
            currentNote = savedInstanceState.getParcelable(CURRENT_NOTE);
        } else {
            // Если воccтановить не удалось, то сделаем объект с первым индексом
            currentNote = new Note();
        }
    }

    private void initViews() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tl_main);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()) {
                    case 0:
                        doActionList();
                        break;
                    case 1:
                        doActionContent();
                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });

        TabLayout.Tab tab = tabLayout.getTabAt(0);
        if (tab != null) {
            tab.select();
        }
    }

    private void doActionList() {
        addFragment(NOTE_NAME_TAG);
    }

    private void doActionContent() {
        if (currentNote != null) {
            // если у нас уже выбрана заметка, и нам есть что показать
            addFragment(NOTE_BODY_TAG);
        } else {
            // если нечего показать(не выбрана заметка), предупреждаем пользователя
            Toast.makeText(this, "Не выбрана ни одна заметка", Toast.LENGTH_SHORT).show();
        }
    }

    private void addFragment(String tag) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = null;

        switch (tag) {
            case NOTE_NAME_TAG:
                fragment = fragmentManager.findFragmentByTag(NOTE_NAME_TAG);
                if (fragment == null) {
                    fragment = new NoteNameFragment();
                }
                break;
            case NOTE_BODY_TAG:
                fragment = fragmentManager.findFragmentByTag(NOTE_BODY_TAG);
                if (fragment == null) {
                    fragment = NoteBodyFragment.newInstance(1);
                }
                break;
        }

        if (fragment != null) {
            fragmentManager.beginTransaction()
                    .replace(R.id.fr_container_main, fragment, tag)
                    .addToBackStack(tag)
                    .commit();
        }
    }

}
