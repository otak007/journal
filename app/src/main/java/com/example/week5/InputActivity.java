package com.example.week5;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class InputActivity extends AppCompatActivity {

    private ImageButton laughingSmiley;
    private ImageButton okSmiley;
    private ImageButton tiredSmiley;
    private ImageButton angrySmiley;

    private String selectedMood;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);

        // Initialise the views
        laughingSmiley = findViewById(R.id.moodLaughing);
        okSmiley = findViewById(R.id.moodOk);
        tiredSmiley = findViewById(R.id.moodTired);
        angrySmiley = findViewById(R.id.moodAngry);

        selectedMood = null;
    }

    // Save the page
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);;

        outState.putBoolean("pressed ok", okSmiley.isPressed());
        outState.putBoolean("pressed laughing", laughingSmiley.isPressed());
        outState.putBoolean("pressed tired", tiredSmiley.isPressed());
        outState.putBoolean("pressed angry", angrySmiley.isPressed());

        outState.putString("selected mood", selectedMood);
    }

    // Preserve the page after rotation
    public void onRestoreInstanceState(Bundle inState) {
        super.onRestoreInstanceState(inState);

        laughingSmiley.setPressed(inState.getBoolean("pressed laughing"));
        okSmiley.setPressed(inState.getBoolean("pressed ok"));
        tiredSmiley.setPressed(inState.getBoolean("pressed tired"));
        angrySmiley.setPressed(inState.getBoolean("pressed angry"));

        selectedMood = inState.getString("selected mood");
        setSmileyGrey(selectedMood);
    }

    // Check which smiley is clicked and call the setSmileyGrey() method
    public void moodClicked(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.moodLaughing:
                selectedMood = "laughing";
                break;

            case R.id.moodAngry:
                selectedMood = "angry";
                break;

            case R.id.moodOk:
                selectedMood = "ok";
                break;

            case R.id.moodTired:
                selectedMood = "tired";
                break;
        }
        setSmileyGrey(selectedMood);
    }

    // Change the color filter of the smiley that is clicked
    public void setSmileyGrey(String selectedMood){

        laughingSmiley.setPressed(false);
        okSmiley.setPressed(false);
        tiredSmiley.setPressed(false);
        angrySmiley.setPressed(false);

        laughingSmiley.setColorFilter(0);
        okSmiley.setColorFilter(0);
        tiredSmiley.setColorFilter(0);
        angrySmiley.setColorFilter(0);

        switch (selectedMood) {
            case "laughing":
                laughingSmiley.setColorFilter(Color.argb(150, 155, 155, 155));
                laughingSmiley.setPressed(true);
                break;

            case "angry":
                angrySmiley.setColorFilter(Color.argb(150, 155, 155, 155));
                angrySmiley.setPressed(true);
                break;

            case "ok":
                okSmiley.setPressed(true);
                okSmiley.setColorFilter(Color.argb(150, 155, 155, 155));
                break;

            case "tired":
                tiredSmiley.setColorFilter(Color.argb(150, 155, 155, 155));
                tiredSmiley.setPressed(true);
                break;
        }
    }


    // Add the new journal to database table and switch to the homepage,
    // when the title and content is filled in and the mood is selected
    public void addEntry(View view){
        EntryDatabase db = EntryDatabase.getInstance(getApplicationContext());

        EditText newTitle = findViewById(R.id.newTitle);
        EditText newText = findViewById(R.id.newText);

        String title = newTitle.getText().toString();
        String content = newText.getText().toString();
        String timestamp = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(Calendar.getInstance().getTime());;
        String mood = selectedMood;
        Log.d("kok", " "+title);

        if (title.equals("")){
            Toast.makeText(this, "Please fill in a title", Toast.LENGTH_SHORT).show();
        }

        else if (content.equals("")){
            Toast.makeText(this, "Please fill in some content", Toast.LENGTH_SHORT).show();
        }

        else if (selectedMood == null){
            Toast.makeText(this, "Please select the mood", Toast.LENGTH_SHORT).show();
        }

        else {
            JournalEntry entry = new JournalEntry(title, content, mood, timestamp);
            db.insert(entry);

            Intent intent = new Intent();
            intent.setClass(InputActivity.this, MainActivity.class);
            startActivity(intent);
        }
    }
}
