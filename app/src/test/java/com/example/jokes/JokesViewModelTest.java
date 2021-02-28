package com.example.jokes;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.MutableLiveData;

import com.example.jokes.entities.Joke;
import com.example.jokes.viewModel.JokesViewModel;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class JokesViewModelTest {

    @Mock
    private JokesViewModel jokesViewModel;
    private Joke joke = new Joke("A01", "https://www.google.com/","https://www.google.com/","I am fun", "23 August 2021","24 August 2021", null);
    private final MutableLiveData<Joke> jokeMutableLiveData = new MutableLiveData<>();

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    @Test
    public void OnGetSelected() {
        jokeMutableLiveData.setValue(joke);

        when(jokesViewModel.OnGetSelected()).thenReturn(jokeMutableLiveData);
        System.out.println(jokeMutableLiveData.getValue().getIcon_url());
    }

    @Test
    public void OnSelected(){
        jokeMutableLiveData.setValue(joke);

        doNothing().when(jokesViewModel).OnSelect(joke);
    }

}
