package com.example.jokes.remote;

import com.example.jokes.entities.Joke;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

public interface RemoteClient {
    @GET("search")
    Observable<List<Joke>> OnSearchJokes(@Query("query") String keyword);

    @GET("random")
    Observable<Joke> OnGetRandomJoke();

}
