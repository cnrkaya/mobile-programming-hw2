package com.example.mobilprogramlama;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
public class PersonAdapter extends RecyclerView.Adapter<PersonAdapter.MyViewHolder> {

    Context context;
    List<Person> persons;

    public PersonAdapter(Context context, List<Person> persons) {
        this.context = context;
        this.persons = persons;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.person_card,parent,false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }


    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
        holder.userName.setText(persons.get(position).getUserName());
        holder.password.setText(persons.get(position).getPassword());
        holder.imageView.setImageResource(persons.get(position).getPhotoId());
        holder.btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean on = ((ToggleButton) v).isChecked();
                if (on) {
                    holder.password.setText("****");
                    // ON durumunda yapılacaklar
                } else {
                    //OFF durumunda yapılacaklar
                    holder.password.setText(persons.get(position).getPassword());
                }

            }
        });
    }

    @Override
    public int getItemCount() {
        return persons.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
    ImageView imageView ;
    TextView userName;
    TextView password;
    ToggleButton btn;
    public MyViewHolder(View itemView){
        super(itemView);
        imageView = itemView.findViewById(R.id.personImage);
        userName = itemView.findViewById(R.id.personUsername);
        password = itemView.findViewById(R.id.personPassword);
        btn = itemView.findViewById(R.id.toggleButton);
    }

    }
}
