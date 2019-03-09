package com.example.week5;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;



public class EntryDatabase extends SQLiteOpenHelper {

    private static EntryDatabase instance;

    //Constructor
    private EntryDatabase(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);

    }

    // Create an empty database table
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE entries(_id INTEGER PRIMARY KEY, title text, content text, mood text, time text)");

    }

    // Upgrade the database table
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS entries;");
        onCreate(db);
    }


    public static EntryDatabase getInstance(Context context){
        if (EntryDatabase.instance == null){
            EntryDatabase.instance = new EntryDatabase(context, "entries", null, 1);
            return EntryDatabase.instance;
        }
        else{
            return EntryDatabase.instance;
        }
    }

    // Return Cursor with entire table
    public static Cursor selectAll(EntryDatabase instance) {
        SQLiteDatabase db = instance.getWritableDatabase();
        return db.rawQuery("SELECT * FROM entries", null);
    }

    // Insert a new journal to the table
    public void insert(JournalEntry entry){
        SQLiteDatabase database = instance.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("title", entry.getTitle());
        values.put("mood", entry.getMood());
        values.put("content", entry.getContent());
        values.put("time", entry.getTimestamp());
        database.insert("entries", null, values);

    }

    // delete an existing entry
    void deleteEntry(long id) {
        SQLiteDatabase db = getWritableDatabase();
        String delete = "DELETE from  entries  WHERE _id= " + id;
        db.execSQL(delete);
    }
}
