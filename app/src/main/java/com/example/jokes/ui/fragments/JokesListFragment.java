package com.example.jokes.ui.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.jokes.R;
import com.example.jokes.adapters.JokeAdapter;
import com.example.jokes.databinding.FragmentJokesListBinding;
import com.example.jokes.entities.Joke;
import com.example.jokes.entities.JokeResponse;
import com.example.jokes.viewModel.JokesViewModel;

import java.util.ArrayList;

public class JokesListFragment extends Fragment {

    /****
     * Global declaration
     */
    private ArrayList<Joke> jokeArrayList = new ArrayList<>();
    private FragmentJokesListBinding binding;
    private JokesViewModel jokesViewModel;
    private JokeAdapter jokeAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentJokesListBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        OnInitialize();
        OnSetupRecyclerView();
        OnCallMethods();
    }

    /****
     * Initialize view or any other component required by the view
     */
    private void OnInitialize(){
        jokesViewModel = new ViewModelProvider(this).get(JokesViewModel.class);
    }

    private void OnSetupRecyclerView(){
        binding.parentRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        binding.parentRecyclerView.setHasFixedSize(true);
        jokeAdapter = new JokeAdapter(getActivity());
    }

    /****
     * Call all methods in the fragments
     */
    private void OnCallMethods(){
        OnSearchJokes("chuck");
    }

    private void OnSearchJokes(String keyword){
        jokesViewModel.OnSearchJokes(keyword).observe(getViewLifecycleOwner(), new Observer<JokeResponse>() {
            @Override
            public void onChanged(JokeResponse jokeResponse) {
                if(jokeResponse != null){
                    jokeArrayList.addAll(jokeResponse.getResult());
                }

                jokeAdapter.submitList(jokeArrayList);
                binding.parentRecyclerView.setAdapter(jokeAdapter);
            }
        });
    }

    private void OnGetRandomJoke(){

    }
}