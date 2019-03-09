package com.example.week5;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent = getIntent();
        String clickedTitle = (String) intent.getSerializableExtra("clicked title");
        String clickedTime = (String) intent.getSerializableExtra("clicked time");
        String clickedContent = (String) intent.getSerializableExtra("clicked content");
        String clickedMood = (String) intent.getSerializableExtra("clicked mood");

        // Initial the views
        TextView titleView = findViewById(R.id.title);
        ImageView moodView = findViewById(R.id.mood);
        TextView timeView = findViewById(R.id.time);
        TextView contentView = findViewById(R.id.content);

        // Set the specific title, time and content to the right view
        titleView.setText(clickedTitle);
        timeView.setText(clickedTime);
        contentView.setText(clickedContent);

        // Set the specific smiley of the journal in the image-view
        switch (clickedMood){
            case "angry":
                moodView.setImageResource(R.drawable.angry);
                break;

            case "ok":
                moodView.setImageResource(R.drawable.ok);
                break;

            case "tired":
                moodView.setImageResource(R.drawable.tired);
                break;

            case "laughing":
                moodView.setImageResource(R.drawable.laughing);
                break;
        }
    }
}
