package com.example.jokes.ui.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.jokes.R;
import com.example.jokes.databinding.FragmentJokeDetailBinding;

public class JokeDetailFragment extends Fragment {

    private FragmentJokeDetailBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
      binding = FragmentJokeDetailBinding.inflate(getLayoutInflater());
      View view = binding.getRoot();

      return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    /****
     * Initialize view or any other component required by the view
     */
    private void OnInitialize(){

    }

    /****
     * Call all methods in the fragments
     */
    private void OnCallMethods(){

    }
}