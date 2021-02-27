package com.example.jokes.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.jokes.entities.Joke;
import com.example.jokes.entities.JokeResponse;
import com.example.jokes.repo.JokesRepository;

public class JokesViewModel extends AndroidViewModel {

    private JokesRepository jokesRepository;

    public JokesViewModel(@NonNull Application application) {
        super(application);

        jokesRepository = JokesRepository.getJokesRepository(application);
    }

    public LiveData<JokeResponse> OnSearchJokes(String keyword) {
        return jokesRepository.OnSearchJokes(keyword);
    }

    public LiveData<Joke> OnGetRandomJoke() {
        return jokesRepository.OnGetRandomJoke();
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        jokesRepository.onDisposeObservable();
    }
}
