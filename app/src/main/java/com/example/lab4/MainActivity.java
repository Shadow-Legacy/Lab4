package com.example.lab4;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<String> notesList;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize notes list
        notesList = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, notesList);

        // Set up ListView
        ListView listView = findViewById(R.id.listView);
        listView.setAdapter(adapter);

        // Handle item click for deleting notes
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Implement logic for deleting the selected note
                String selectedNote = notesList.get(position);
                // Add your logic to delete the note
                notesList.remove(position);
                adapter.notifyDataSetChanged();
                Toast.makeText(MainActivity.this, "Note deleted: " + selectedNote, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_add_note:
                // Open AddNoteActivity when Add Note is selected
                Intent addNoteIntent = new Intent(this, AddNoteActivity.class);
                startActivityForResult(addNoteIntent, 1);
                return true;
            case R.id.menu_delete_note:
                // Open DeleteNoteActivity when Delete Note is selected
                Intent deleteNoteIntent = new Intent(this, DeleteNoteActivity.class);
                deleteNoteIntent.putStringArrayListExtra("notesList", notesList);
                startActivityForResult(deleteNoteIntent, 2);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    // Handle result from AddNoteActivity or DeleteNoteActivity
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == 1) { // AddNoteActivity
                String newNote = data.getStringExtra("newNote");
                notesList.add(newNote);
                adapter.notifyDataSetChanged();
                Toast.makeText(this, "Note added: " + newNote, Toast.LENGTH_SHORT).show();
            } else if (requestCode == 2) { // DeleteNoteActivity
                ArrayList<String> updatedNotesList = data.getStringArrayListExtra("updatedNotesList");
                notesList.clear();
                notesList.addAll(updatedNotesList);
                adapter.notifyDataSetChanged();
            }
        }
    }
}