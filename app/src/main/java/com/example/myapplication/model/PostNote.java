package com.example.myapplication.model;

public class PostNote {
    private static String message;
    private static String data;

    public PostNote(String message, String data) {
        this.message = message;
        this.data = data;
    }

    public static String getMessage() {
        return message;
    }

    public static String getData() {
        return data;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "PostNote{" +
                "message='" + message + '\'' +
                ", data='" + data + '\'' +
                '}';
    }
}
