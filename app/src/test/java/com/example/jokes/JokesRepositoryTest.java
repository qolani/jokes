package com.example.jokes;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.MutableLiveData;

import com.example.jokes.entities.Joke;
import com.example.jokes.entities.JokeResponse;
import com.example.jokes.repo.JokesRepository;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class JokesRepositoryTest {

    @Mock
    private JokesRepository jokesRepository;
    private Joke joke = new Joke("A01", "https://www.google.com/","https://www.google.com/","I am fun", "23 August 2021","24 August 2021", null);
    private final MutableLiveData<Joke> jokeMutableLiveData = new MutableLiveData<>();
    private final MutableLiveData<JokeResponse> jokeResponseMutableLiveData = new MutableLiveData<>();

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    @Test
    public void OnGetRandomJoke() {
        jokeMutableLiveData.setValue(joke);

        when(jokesRepository.OnGetRandomJoke()).thenReturn(jokeMutableLiveData);
        System.out.println(jokeMutableLiveData.getValue().getValue());
    }

    @Test
    public void OnSearchJoke(){

        JokeResponse jokeResponse = new JokeResponse();
        List<Joke> list = new ArrayList<>();

        list.add(joke);
        jokeResponse.setTotal(9937);
        jokeResponse.setResult(list);
        jokeResponseMutableLiveData.setValue(jokeResponse);

        when(jokesRepository.OnSearchJokes("chuck")).thenReturn(jokeResponseMutableLiveData);
        System.out.println(jokeResponseMutableLiveData.getValue().getResult().get(0).getUrl());
    }
}
