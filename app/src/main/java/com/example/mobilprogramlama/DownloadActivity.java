package com.example.mobilprogramlama;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;


import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;

import java.util.Random;

public class DownloadActivity extends AppCompatActivity {
    Button download;
    ImageView img_icon;
    ProgressBar progressBar;
    int progressStatus;
    AsyncTaskExample imageDownload;
    Ringtone newRingtone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download);
        download = findViewById(R.id.download);
        img_icon = findViewById(R.id.img_icon);
        progressBar = (ProgressBar) findViewById(R.id.progressbar);
        progressBar.setVisibility(View.INVISIBLE);



        download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageDownload =  new AsyncTaskExample();
                progressBar.setVisibility(View.VISIBLE);
                imageDownload.execute();

            }
        });
    }


    private class AsyncTaskExample extends AsyncTask<String,String, Boolean> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            //RelativeLayout layout = findViewById(R.id.myLayout);
        }

        @Override
        protected Boolean doInBackground(String... strings) {
            Random random = new Random();

            progressStatus = 0;
            while (100 >= progressStatus) {
                progressBar.setProgress(progressStatus);
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                progressStatus += random.nextInt(10);
            }
            progressBar.setProgress(100);

            return true;
        }
        @Override
        protected void onPostExecute(Boolean finished) {
            img_icon.setVisibility(View.VISIBLE);
            int newRingtoneType = RingtoneManager.TYPE_NOTIFICATION;
            newRingtone = RingtoneManager.getRingtone(getApplicationContext(),
                    RingtoneManager.getDefaultUri(newRingtoneType));
            newRingtone.play();
            return;
        }
    }


}
