package com.example.jokes.interfaces;

import androidx.lifecycle.MutableLiveData;

public interface IJokesRepository {
    MutableLiveData<Long> OnGetSearchJokes(String keyword);
}
