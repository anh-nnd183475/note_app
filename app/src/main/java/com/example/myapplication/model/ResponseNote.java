package com.example.myapplication.model;

import java.util.List;

public class ResponseNote {
    private String message;
    private List<Note> data;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Note> getData() {
        return data;
    }

    public void setData(List<Note> data) {
        this.data = data;
    }
}
