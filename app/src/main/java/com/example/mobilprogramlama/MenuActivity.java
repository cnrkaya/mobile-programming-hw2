package com.example.mobilprogramlama;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

public class MenuActivity extends AppCompatActivity implements View.OnClickListener{
private ImageButton sensors,users,notes,settings,logout,email;
private String currentUserName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        currentUserName = getIntent().getStringExtra("userId");
        defineVariables();
        defineListeners();
        Toast.makeText(this, "Hoşgeldin " + currentUserName , Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.users:
                intent = new Intent(v.getContext(),RecylerViewActivity.class);
                intent.putExtra("userId",currentUserName);
                startActivity(intent);
                break;

            case R.id.sensors:
                intent = new Intent(v.getContext(),SensorsActivity.class);
                startActivity(intent);
                break;

            case R.id.notes:
                intent = new Intent(v.getContext(),NotepadActivity.class);
                intent.putExtra("userId",currentUserName);
                startActivity(intent);
                break;
            case R.id.settings:
                intent = new Intent(v.getContext(), UserSettingsActivity.class);
                intent.putExtra("userId",currentUserName);
                startActivity(intent);
                break;
            case R.id.email:
                intent = new Intent(v.getContext(),MailActivity.class);
                startActivity(intent);
                break;
            case R.id.logout:
                intent = new Intent(v.getContext(),MainActivity.class);
                finish();
                startActivity(intent);
                break;

            default:
                break;

        }
    }
    public void defineVariables(){

        sensors = (ImageButton) findViewById(R.id.sensors);
        users = (ImageButton) findViewById(R.id.users);
        notes = (ImageButton) findViewById(R.id.notes);
        settings = (ImageButton) findViewById(R.id.settings);
        logout = (ImageButton) findViewById(R.id.logout);
        email = (ImageButton) findViewById(R.id.email);
    }
    public void defineListeners(){
        sensors.setOnClickListener(this);
        users.setOnClickListener(this);
        notes.setOnClickListener(this);
        settings.setOnClickListener(this);
        logout.setOnClickListener(this);
        email.setOnClickListener(this);
    }
}
