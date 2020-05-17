package com.example.mobilprogramlama;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class UserSettingsActivity extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener {
    SharedPreferences pref;
    Context context;
    Button update;
    TextView username, heightText, weightText, ageText;
    SeekBar height_seekBar,weight_seekBar,age_seekBar;
    SharedPreferences.Editor editor;
    Switch mode;
    RadioButton man,woman;
    private String currentUserName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_settings);

        currentUserName= getIntent().getStringExtra("userId");
        defineVariables();
        getSharedPreferences();
        setListeners();

    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        switch (seekBar.getId()) {

            case R.id.height_seekBar:
                heightText.setText(String.valueOf(progress));
                break;
            case R.id.weight_seekBar:
                weightText.setText(String.valueOf(progress));
                break;
            case R.id.age_seekBar:
                ageText.setText(String.valueOf(progress));
                break;
            default:
                break;
        }
    }

    public void defineVariables(){
        man = findViewById(R.id.man);
        woman = findViewById(R.id.woman);
        height_seekBar = findViewById(R.id.height_seekBar);
        weight_seekBar = findViewById(R.id.weight_seekBar);
        age_seekBar = findViewById(R.id.age_seekBar);
        heightText = findViewById(R.id.heightText);
        weightText = findViewById(R.id.weightText);
        ageText = findViewById(R.id.ageText);
        mode = findViewById(R.id.mode);
        update = findViewById(R.id.update);
        username = findViewById(R.id.username);
    }

    public void getSharedPreferences(){
        context = getApplicationContext();
        pref = context.getSharedPreferences(currentUserName, context.MODE_PRIVATE);
        editor = pref.edit();

        //Getting values from shared preferences
        String height = pref.getString("height", "0");
        String weight = pref.getString("weight", "0");
        String age = pref.getString("age", "0");
        String sex = pref.getString("sex","none");
        Boolean nightMode = pref.getBoolean("mode",false);

        /*Setting values*/
        username.setText("Kullanıcı Adı : " + currentUserName);
        //sex
        if(sex.equals("man"))
            man.setChecked(true);
        if(sex.equals("woman"))
            woman.setChecked(true);
        //day mode
        mode.setChecked(nightMode);
        if(nightMode)
            mode.setText("Gece Modu Aktif");
        else
            mode.setText("Gündüz Modu Aktif");
        //height,weight,age
        heightText.setText(height);
        height_seekBar.setProgress(Integer.valueOf(height));
        weightText.setText(weight);
        weight_seekBar.setProgress(Integer.valueOf(weight));
        ageText.setText(age);
        age_seekBar.setProgress(Integer.valueOf(age));
    }

    public void setListeners(){
        /*Setting listeners*/
        height_seekBar.setOnSeekBarChangeListener(this);
        weight_seekBar.setOnSeekBarChangeListener(this);
        age_seekBar.setOnSeekBarChangeListener(this);

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.putString("height", heightText.getText().toString());
                editor.putString("weight", weightText.getText().toString());
                editor.putString("age", ageText.getText().toString());
                if(man.isChecked())
                    editor.putString("sex","man");
                if(woman.isChecked())
                    editor.putString("sex","woman");
                editor.putBoolean("mode",mode.isChecked());
                editor.commit();
                Toast.makeText(getApplicationContext(),
                        "Bilgiler Kaydedildi", Toast.LENGTH_SHORT).show();
            }
        });

        mode.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    mode.setText("Gece Modu Aktif");
                } else {
                    mode.setText("Gündüz Modu Aktif");
                }
            }
        });
    }


    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }

}
