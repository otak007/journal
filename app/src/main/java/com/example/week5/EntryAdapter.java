package com.example.week5;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ResourceCursorAdapter;
import android.widget.TextView;



public class EntryAdapter extends ResourceCursorAdapter {

    public EntryAdapter(Context context, int layout, Cursor cursor) {
        super(context, layout, cursor);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        // Get the data of the journal
        String title = cursor.getString(cursor.getColumnIndex("title"));
        String time = cursor.getString(cursor.getColumnIndex("time"));
        String mood = cursor.getString(cursor.getColumnIndex("mood"));

        // Initial the text and image views
        TextView titleView = view.findViewById(R.id.title);
        TextView timeView = view.findViewById(R.id.time);
        ImageView moodView = view.findViewById(R.id.moodView);

        // Set the title and time of the journal in the specific textview
        titleView.setText(title);
        timeView.setText(time);

        // Set the specific smiley of the journal in the image-view
        switch (mood){
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
