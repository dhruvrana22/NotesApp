package com.example.notesapp.models;

public class User {
    private int id;
    private String username;
    private static String currentUserName;

    public User() {
    }

    public static String getCurrentUserName() {
        return currentUserName;
    }

    public static void setCurrentUserName(String currentUserName) {
        User.currentUserName = currentUserName;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
