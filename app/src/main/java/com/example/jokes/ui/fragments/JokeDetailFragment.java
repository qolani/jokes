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
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.jokes.R;
import com.example.jokes.adapters.CategoryAdapter;
import com.example.jokes.databinding.FragmentJokeDetailBinding;
import com.example.jokes.entities.Joke;
import com.example.jokes.viewModel.JokesViewModel;

import java.util.ArrayList;
import java.util.List;

public class JokeDetailFragment extends Fragment {

    /****
     * Property declaration
     */
    private FragmentJokeDetailBinding binding;
    private CategoryAdapter categoryAdapter;
    private JokesViewModel jokesViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
      binding = FragmentJokeDetailBinding.inflate(getLayoutInflater());
      View view = binding.getRoot();
      setHasOptionsMenu(true);

      return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        OnInitialize();
        OnSetRecyclerView();
        OnSetUI();
    }

    /****
     * Initialize view or any other component required by the view
     */
    private void OnInitialize(){
        jokesViewModel = new ViewModelProvider(getActivity()).get(JokesViewModel.class);

    }

    private void OnSetRecyclerView(){
        binding.detailRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        binding.detailRecyclerView.setHasFixedSize(true);
        categoryAdapter = new CategoryAdapter(getActivity());
    }

    private void OnSetUI(){
        jokesViewModel.OnGetSelected().observe(getViewLifecycleOwner(), new Observer<Joke>() {
            @Override
            public void onChanged(Joke joke) {
                binding.setJoke(joke);
                binding.setImage(joke.getIcon_url());
                OnLoadCategories(joke.getCategoriesList());
            }
        });
    }

    private void OnLoadCategories(List<String> list){

        if(list.size()>0){
                categoryAdapter.submitList(list);
            } else {
            list.add("No category found");
            }

            binding.detailRecyclerView.setAdapter(categoryAdapter);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        menu.clear();
    }

}