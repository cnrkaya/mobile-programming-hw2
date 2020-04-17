package com.example.mobilprogramlama;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText username ;
    EditText password ;
    TextView textMessage;
    Button buton_sign;
    ArrayList<Person> persons;
    Integer attempt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        defineVariables();
        defineListeners();
    }

    private void cleanTextBoxes(){
        username.setText("");
        password.setText("");
    }
    private boolean checkPerson(){
        for(Person aPerson : persons){
            if( username.getText().toString().equals(aPerson.getUserName())
                    && password.getText().toString().equals((aPerson.getPassword())))
                return true;
        }
        return false;

    }

    public void defineVariables(){
        attempt=0;
        persons = Person.getPersonsList();
        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        buton_sign = (Button) findViewById(R.id.button_sign);
        textMessage = (TextView) findViewById(R.id.textMessage);
    }

    public void defineListeners(){

        buton_sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                if(checkPerson()) {
                    intent = new Intent(v.getContext(), MenuActivity.class);
                    intent.putExtra("userId",username.getText().toString());
                    cleanTextBoxes();
                    startActivity(intent);
                }
                else {
                    cleanTextBoxes();
                    attempt +=1;
                    textMessage.setText("Kullanıcı Adı/Parola Hatalı");
                    if(attempt >= 3){
                        Toast.makeText(MainActivity.this, "3 defa hatalı giriş yapıldı uygulama sonlandırılıyor"
                                , Toast.LENGTH_SHORT).show();
                        finish();
                    }

                }
            }
        });
    }

}
