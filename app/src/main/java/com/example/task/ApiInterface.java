package com.example.task;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("/photos")
    Call<List<data>> getTodos();

    @GET("/todos/{id}")
    Call<data> getTodo(@Path("id") int id);

    @GET("/todos")
    Call<List<data>> getTodosUsingQuery(@Query("userId") int userId, @Query("completed") boolean completed);

    @POST("/todos")
    Call<data> postTodo(@Body  data todo);

}