package com.example.week5;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;


public class MainActivity extends AppCompatActivity {

    private ListView entriesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        updateData();

        entriesList.setOnItemClickListener(new OnItemClickListener());
        entriesList.setOnItemLongClickListener(new OnItemLongClickListener());
    }

    // By clicking on the "plus button", the input page will be shown
    public void newJournal(View view) {
        Intent intent = new Intent();
        intent.setClass(MainActivity.this, InputActivity.class);
        startActivity(intent);
    }

    // update the activity based on last known data
    public void updateData() {
        EntryDatabase db = EntryDatabase.getInstance(getApplicationContext());
        EntryAdapter adapter = new EntryAdapter(this, R.layout.entry_row, EntryDatabase.selectAll(db));
        entriesList = findViewById(R.id.journals);
        entriesList.setAdapter(adapter);
    }


    // on long click, delete journal entry from database and update the activity
    private class OnItemLongClickListener implements AdapterView.OnItemLongClickListener {
        @Override
        public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
            EntryDatabase db = EntryDatabase.getInstance(getApplicationContext());
            Cursor cursor = (Cursor) adapterView.getItemAtPosition(i);
            long id = cursor.getInt(cursor.getColumnIndex("_id"));

            db.deleteEntry(id);
            updateData();
            return false;
        }
    }

    private class OnItemClickListener implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Cursor cursor = (Cursor) parent.getItemAtPosition(position);

            String title = cursor.getString(cursor.getColumnIndex("title"));
            String time = cursor.getString(cursor.getColumnIndex("time"));
            String mood = cursor.getString(cursor.getColumnIndex("mood"));
            String content = cursor.getString(cursor.getColumnIndex("content"));

            Intent intent = new Intent();
            intent.setClass(MainActivity.this, DetailActivity.class);
            intent.putExtra("clicked title", title);
            intent.putExtra("clicked time", time);
            intent.putExtra("clicked content", content);
            intent.putExtra("clicked mood", mood);
            startActivity(intent);
        }
    }
}