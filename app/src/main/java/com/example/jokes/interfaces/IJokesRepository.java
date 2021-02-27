package com.example.jokes.interfaces;

import androidx.lifecycle.MutableLiveData;

import com.example.jokes.entities.Joke;
import com.example.jokes.entities.JokeResponse;

import java.util.List;

public interface IJokesRepository {
    MutableLiveData<JokeResponse> OnSearchJokes(String keyword);
    MutableLiveData<Joke> OnGetRandomJoke();
}
