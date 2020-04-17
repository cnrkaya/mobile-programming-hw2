package com.example.mobilprogramlama;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MailActivity extends AppCompatActivity {

    EditText sentAddress ;
    EditText header;
    EditText body;
    Button button_sent,attachment;
    TextView txtAttachment;
    Uri URI = null;
    private static final int PICK_FROM_GALLERY = 101;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mail);
        defineVariables();
        defineListeners();

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PICK_FROM_GALLERY && resultCode == RESULT_OK) {
            URI = data.getData();
            txtAttachment.setText(URI.getLastPathSegment());
            txtAttachment.setVisibility(View.VISIBLE);
        }
    }

    public void defineVariables(){
        sentAddress = (EditText)findViewById(R.id.sentAddress);
        header = (EditText)findViewById(R.id.header);
        body = (EditText)findViewById(R.id.body);
        button_sent= (Button)findViewById(R.id.button_sent);
        attachment = (Button) findViewById(R.id.attachment);
        txtAttachment = findViewById(R.id.tvAttachment);
    }

    public void defineListeners(){
        button_sent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/html");

                intent.putExtra(Intent.EXTRA_EMAIL, new String[] {sentAddress.getText().toString()});
                intent.putExtra(Intent.EXTRA_SUBJECT, header.getText().toString());
                intent.putExtra(Intent.EXTRA_TEXT, body.getText().toString());

                if (URI != null) {
                    intent.putExtra(Intent.EXTRA_STREAM, URI);
                }

                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(Intent.createChooser(intent, "Send Email"));
                }
            }
        });

        attachment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                intent.putExtra("return-data", true);
                startActivityForResult(Intent.createChooser(intent, "Complete action using"), PICK_FROM_GALLERY);

            }
        });
    }
}
