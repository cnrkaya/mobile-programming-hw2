package com.example.mobilprogramlama;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;


import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;


public class NotepadActivity extends AppCompatActivity {

    Button update_button;
    EditText noteField;
    String fileName;
    String currentUsername;
    ImageButton delete;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notepad);

        currentUsername= getIntent().getStringExtra("userId");
        defineVariables();

        try {
            FileInputStream fis = openFileInput(currentUsername);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);

            String str;
            StringBuilder buf = new StringBuilder();
            while ((str = br.readLine()) != null) {
                buf.append(str + "\n");
            }
            fis.close();

            noteField.setText(buf.toString());

        }catch (Exception e){
            e.printStackTrace();
        }

        defineListeners();

    }

    public void saveNoteIntoStorage(){
        try{
            FileOutputStream fos = openFileOutput(currentUsername,Context.MODE_PRIVATE);
            fos.write(noteField.getText().toString().getBytes());
            fos.close();
            Toast.makeText(NotepadActivity.this, "Not Kaydedildi", Toast.LENGTH_SHORT).show();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void defineVariables(){
        update_button = (Button)findViewById(R.id.update);
        noteField = (EditText) findViewById(R.id.noteField);
        delete= (ImageButton) findViewById(R.id.delete);
    }

    public void defineListeners(){
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                noteField.setText("");
                saveNoteIntoStorage();
            }
        });

        update_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveNoteIntoStorage();
            }
        });
    }
}
