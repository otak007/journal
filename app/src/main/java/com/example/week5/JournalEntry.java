package com.example.week5;

import java.io.Serializable;

public class JournalEntry implements Serializable {
    private String title, content, mood, timestamp;
    private int id;

    //constructor
    public JournalEntry(String title, String content, String mood, String timestamp) {
        this.title = title;
        this.content = content;
        this.timestamp = timestamp;
        this.mood = mood;

    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getMood() {
        return mood;
    }

    public String getTimestamp() {
        return timestamp;
    }
}
