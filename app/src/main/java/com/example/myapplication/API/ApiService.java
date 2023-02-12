package com.example.myapplication.API;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

import com.example.myapplication.model.Note;
import com.example.myapplication.model.ResponseNote;
import com.google.gson.GsonBuilder;
import com.google.gson.Gson;
public interface ApiService {
    Gson gson = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd HH:mm:ss")
            .create();
// KHởi tạo retrofit, converter sử dụng gson
    ApiService apiService = new Retrofit.Builder()
             .baseUrl("http://raspianhnq.ddns.net:8080/")
             .addConverterFactory(GsonConverterFactory.create(gson))
             .build()
             .create(ApiService.class);
// hàm call api phương thức GET
    @GET("notes/fetchallnotes")
    Call<ResponseNote> getNotes(@Query("id_user") int idUser);

    @FormUrlEncoded
    @POST("notes/create")
    Call<Note> createNote(
            @Field("id_user") int idUser,
            @Field("title") String title,
            @Field("content") String content,
            @Field("date_create") String dateCreate,
            @Field("date_modify") String dateModify
    );



}
