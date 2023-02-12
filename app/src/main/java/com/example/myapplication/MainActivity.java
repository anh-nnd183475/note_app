package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.myapplication.API.ApiService;
import com.example.myapplication.adapter.NoteAdapter;
import com.example.myapplication.model.Note;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import com.example.myapplication.model.ResponseNote;
import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rcvNote;
    private List<Note> data;
    private List<Note> notes = new ArrayList<>();
//    private NoteAdapter noteAdapter;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rcvNote = findViewById(R.id.recyclerview);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rcvNote.setLayoutManager(linearLayoutManager);

        DividerItemDecoration itemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        rcvNote.addItemDecoration(itemDecoration);

        callApiGetNote();

        MaterialButton addNewNoteBtn = findViewById(R.id.addnewnotebtn);
        addNewNoteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent;
                intent = new Intent(MainActivity.this, AddNoteActivity.class);
                startActivity(intent);
            }
        });
    }


    private void callApiGetNote(){
//        noteAdapter = new NoteAdapter(notes);
//        rcvNote.setAdapter(noteAdapter);

        ApiService.apiService.getNotes(1).enqueue(new Callback<ResponseNote>() {
            @Override
            public void onResponse(Call<ResponseNote> call, Response<ResponseNote> response) {

                    List<Note> data = response.body().getData();

                    NoteAdapter noteAdapter = new NoteAdapter(data);
                    rcvNote.setAdapter(noteAdapter);
                    notes.clear();
                    notes.addAll(data);
                    noteAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<ResponseNote> call, Throwable t) {
                Toast.makeText(MainActivity.this, "onFailure", Toast.LENGTH_SHORT).show();
            }
        });
    }
}