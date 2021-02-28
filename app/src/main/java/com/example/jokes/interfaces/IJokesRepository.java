package com.example.jokes.interfaces;

import androidx.lifecycle.MutableLiveData;

import com.example.jokes.entities.Joke;
import com.example.jokes.entities.JokeResponse;

public interface IJokesRepository {
    /**** Empty body methods
     *
     * @param keyword
     * @return
     */
    MutableLiveData<JokeResponse> OnSearchJokes(String keyword);
    MutableLiveData<Joke> OnGetRandomJoke();
    void OnDisposeObservable();
}
