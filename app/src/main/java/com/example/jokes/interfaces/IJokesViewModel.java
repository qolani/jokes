package com.example.jokes.interfaces;

import androidx.lifecycle.LiveData;

import com.example.jokes.entities.Joke;

public interface IJokesViewModel {
    /**** Empty body methods
     *
     * @return
     */
    LiveData<Joke> OnGetSelected();
    void OnSelect(Joke item);
}
