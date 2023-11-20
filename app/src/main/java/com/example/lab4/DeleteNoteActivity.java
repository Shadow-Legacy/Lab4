package com.example.lab4;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class DeleteNoteActivity extends AppCompatActivity {

    private ArrayList<String> notesList;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_note);

        notesList = getIntent().getStringArrayListExtra("notesList");
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_multiple_choice, notesList);

        ListView listView = findViewById(R.id.deleteListView);
        listView.setAdapter(adapter);
        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

        Button deleteButton = findViewById(R.id.deleteButton);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get selected items and remove them from the notes list
                ArrayList<String> selectedNotes = new ArrayList<>();
                for (int i = 0; i < listView.getCount(); i++) {
                    if (listView.isItemChecked(i)) {
                        selectedNotes.add(notesList.get(i));
                    }
                }

                // Update the notes list and return the result to MainActivity
                notesList.removeAll(selectedNotes);
                adapter.notifyDataSetChanged();

                Intent resultIntent = new Intent();
                resultIntent.putStringArrayListExtra("updatedNotesList", notesList);
                setResult(RESULT_OK, resultIntent);
                finish();

                Toast.makeText(DeleteNoteActivity.this, "Notes deleted", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
