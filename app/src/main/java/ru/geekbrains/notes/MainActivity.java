package ru.geekbrains.notes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    public static final String CURRENT_NOTE = "CurrentNote";
    public Note currentNote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState != null) {
            // Восстановление текущей позиции.
            currentNote = savedInstanceState.getParcelable(CURRENT_NOTE);
        } else {
            // Если воccтановить не удалось, то сделаем объект с первым индексом
            currentNote = new Note(0);
        }

        //наша навигация оправдана только для портретной ориентации
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            initView();
        }
        initToolbar();

    }
    private void initToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

    }
    private void initView() {
        initButtonNoteList();
        initButtonNoteBody();
    }


    //кнопка открыващая спискок заметок
    private void initButtonNoteList() {
        Button buttonNoteList = findViewById(R.id.note_list);
        buttonNoteList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addFragment(new NoteNameFragment());
            }
        });
    }

    //кнопка открыващая содержимое заметки
    private void initButtonNoteBody() {
        Button buttonNoteBody = findViewById(R.id.note_content);
        buttonNoteBody.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentNote != null) { // если у нас уже выбрана заметка, и нам есть что показать
                    addFragment(NoteBodyFragment.newInstance(currentNote));
                } else { // если нечего показать(не выбрана заметка), предупреждаем пользователя
                    Toast.makeText(getApplicationContext(), "Не выбрана ни одна заметка", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private void addFragment(Fragment fragment) {
        //Получить менеджер фрагментов
        FragmentManager fragmentManager = getSupportFragmentManager();
        // Открыть транзакцию
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        //поместить нужный фрагмент в контейнер
        fragmentTransaction.replace(R.id.fragment_container, fragment);
        //применить изменения
        fragmentTransaction.commit();
    }
}