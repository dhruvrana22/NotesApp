package com.example.notesapp.models;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class Note implements Serializable {
    private int id;
    private String title;
    private String content;
    private String userName;

    public Note(int id, String title, String content, String userName) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.userName = userName;
    }

    public Note() {}

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @NonNull
    @Override
    public String toString() {
        return "id: " + this.id + ", title: " + this.title + ", content: " + this.content + ", username: " + this.userName;
    }

}
