package com.example.lab4;

import android.content.Intent;
import android.widget.Button;
import android.widget.EditText;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AddNoteActivityUnitTest {

    @Mock
    private EditText mockNoteNameEditText;

    @Mock
    private EditText mockNoteContentEditText;

    @Mock
    private Button mockSaveButton;

    private AddNoteActivity addNoteActivity;

    @Before
    public void setUp() {
        addNoteActivity = Mockito.spy(AddNoteActivity.class);
        when(addNoteActivity.findViewById(R.id.noteNameEditText)).thenReturn(mockNoteNameEditText);
        when(addNoteActivity.findViewById(R.id.noteContentEditText)).thenReturn(mockNoteContentEditText);
        when(addNoteActivity.findViewById(R.id.saveButton)).thenReturn(mockSaveButton);
    }

    @Test
    public void testSaveButtonClicked_withNonEmptyFields_setsResultAndFinishesActivity() {
        // Mocking the text in the EditText fields
        when(mockNoteNameEditText.getText()).thenReturn(new Editable.Factory().newEditable("Title"));
        when(mockNoteContentEditText.getText()).thenReturn(new Editable.Factory().newEditable("Content"));

        // Trigger the saveButton click
        addNoteActivity.onClick(mockSaveButton);

        // Verify that setResult and finish are called with the correct parameters
        verify(addNoteActivity).setResult(RESULT_OK, Mockito.any(Intent.class));
        verify(addNoteActivity).finish();
    }

    @Test
    public void testSaveButtonClicked_withEmptyFields_showsWarningToast() {
        // Mocking empty text in the EditText fields
        when(mockNoteNameEditText.getText()).thenReturn(new Editable.Factory().newEditable(""));
        when(mockNoteContentEditText.getText()).thenReturn(new Editable.Factory().newEditable(""));

        // Trigger the saveButton click
        addNoteActivity.onClick(mockSaveButton);

        // Verify that a warning Toast is shown
        verify(addNoteActivity).showToast(Mockito.any(String.class));
    }
}