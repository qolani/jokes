package com.example.jokes.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.jokes.entities.Joke;
import com.example.jokes.entities.JokeResponse;
import com.example.jokes.repo.JokesRepository;

public class JokesViewModel extends AndroidViewModel {
    /****
     * Property declaration
     */
    private JokesRepository jokesRepository;

    /****
     * Constructor
     * @param application
     */
    public JokesViewModel(@NonNull Application application) {
        super(application);

        /****
         * Initialize the domain layer
         */
        jokesRepository = JokesRepository.getJokesRepository(application);
    }

    /*****
     * Observe changes from the domain layer
     * Search for jokes is called from the repository
     * @param keyword
     * @return
     */
    public LiveData<JokeResponse> OnSearchJokes(String keyword) {
        return jokesRepository.OnSearchJokes(keyword);
    }

    /*****
     * Observe changes from the domain layer
     * Get random joke is called from the repository
     * @return
     */
    public LiveData<Joke> OnGetRandomJoke() {
        return jokesRepository.OnGetRandomJoke();
    }

    /****
     * Dispose objects when view model is cleared
     */
    @Override
    protected void onCleared() {
        super.onCleared();
        jokesRepository.onDisposeObservable();
    }
}
