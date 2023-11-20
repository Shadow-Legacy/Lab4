package com.example.lab4;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class AddNoteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        final EditText noteNameEditText = findViewById(R.id.noteNameEditText);
        final EditText noteContentEditText = findViewById(R.id.noteContentEditText);
        Button saveButton = findViewById(R.id.saveButton);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String noteName = noteNameEditText.getText().toString().trim();
                String noteContent = noteContentEditText.getText().toString().trim();

                if (!noteName.isEmpty() && !noteContent.isEmpty()) {
                    Intent resultIntent = new Intent();
                    resultIntent.putExtra("newNote", noteName + ": " + noteContent);
                    setResult(RESULT_OK, resultIntent);
                    finish();
                } else {
                    // Show a warning using Toast if fields are empty
                    Toast.makeText(AddNoteActivity.this, "Note name and content cannot be empty", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}