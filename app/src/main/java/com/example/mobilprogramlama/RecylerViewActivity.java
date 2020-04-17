package com.example.mobilprogramlama;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import java.util.ArrayList;
import java.util.List;

public class RecylerViewActivity extends AppCompatActivity {
    RecyclerView.LayoutManager layoutManager;
    List<Person> persons;
    RecyclerView recyclerView;
    PersonAdapter adp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyler_view);

        defineVariables();
    }

    public void defineVariables(){

        recyclerView = (RecyclerView) findViewById(R.id.recylerview);
        layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        persons = new ArrayList<>();
        persons = Person.getPersonsList();
        adp = new PersonAdapter(this,persons);
        recyclerView.setAdapter(adp);

    }
}
