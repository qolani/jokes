package com.example.jokes.viewModel;

import android.app.Application;
import android.os.Looper;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.jokes.entities.Joke;
import com.example.jokes.entities.JokeResponse;
import com.example.jokes.interfaces.IJokesViewModel;
import com.example.jokes.repo.JokesRepository;
import com.example.jokes.utils.AppExecutor;

import java.util.concurrent.Executor;

public class JokesViewModel extends AndroidViewModel implements IJokesViewModel {
    /****
     * Property declaration
     */
    private final MutableLiveData<Joke> jokeMutableLiveData = new MutableLiveData<>();
    private JokesRepository jokesRepository;
    private AppExecutor appExecutor;

    /****
     * Constructor
     * @param application
     */
    public JokesViewModel(@NonNull Application application) {
        super(application);

        /****
         * Initialize the domain layer and appExecutor
         */
        appExecutor = (AppExecutor) application.getApplicationContext();

        if(appExecutor != null) {
            jokesRepository = JokesRepository.getJokesRepository(application, appExecutor.threadPoolExecutor);
        }
    }

    /*****
     * Observe changes from the domain layer
     * Search for jokes is called from the repository
     * @param keyword
     * @return
     */
    public LiveData<JokeResponse> OnSearchJokes(String keyword) {
        return jokesRepository.OnSearchJokesBG(keyword);
    }

    /*****
     * Observe changes from the domain layer
     * Get random joke is called from the repository
     * @return
     */
    public LiveData<Joke> OnGetRandomJoke() {
        return jokesRepository.OnGetRandomJokeBG();
    }

    /****
     * Dispose objects when view model is cleared
     */
    @Override
    protected void onCleared() {
        super.onCleared();
        jokesRepository.OnDisposeObservable();
    }

    @Override
    public LiveData<Joke> OnGetSelected() {
        return jokeMutableLiveData;
    }

    @Override
    public void OnSelect(Joke joke) {
        jokeMutableLiveData.setValue(joke);
    }
}
