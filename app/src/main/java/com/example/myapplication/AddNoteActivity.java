package com.example.myapplication;
import com.example.myapplication.API.ApiService;
import com.example.myapplication.model.Note;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

import java.io.BufferedWriter;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

public class AddNoteActivity extends Activity {
    private EditText edtTitle;
    private EditText edtDescription;
    private String dateCreate;
    private String dateModify;
    private MaterialButton btnsubmit;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);
        edtTitle = findViewById(R.id.titleinput);
        edtDescription = findViewById(R.id.descriptioninput);
        btnsubmit = findViewById(R.id.savebtn);

        btnsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title = edtTitle.getText().toString();
                String content = edtDescription.getText().toString();
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
                Date date = new Date();
                dateCreate = dateFormat.format(date);
                dateModify = dateCreate;
                // Tạo Retrofit instance
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("http://raspianhnq.ddns.net:8080/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                // Tạo ApiService instance
                ApiService apiService = retrofit.create(ApiService.class);

                // Gửi yêu cầu POST
                Call<Note> call = apiService.createNote(1, title, content, dateCreate, dateModify);
                call.enqueue(new Callback<Note>() {
                    @Override
                    public void onResponse(Call<Note> call, Response<Note> response) {
                        if (response.isSuccessful()) {
                            // Thành công, xử lý kết quả
                            Intent intent;
                            intent = new Intent(AddNoteActivity.this, MainActivity.class);
                            startActivity(intent);
                            // ...
                        } else {
                            // Thất bại, xử lý lỗi
                            // ..
                        }
                    }

                    @Override
                    public void onFailure(Call<Note> call, Throwable t) {
                        Log.e("CreateNote", t.getMessage());
                        Toast.makeText(AddNoteActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }

        });
    }
}

