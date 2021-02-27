package com.example.jokes.ui.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
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
     * Property declaration
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
        /****
         * Enable page top right menu items
         */
        setHasOptionsMenu(true);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        /****
         * Call methods
         */
        OnInitialize();
        OnSetupRecyclerView();

        /****
         * Call for a random joke if search list is empty
         */
        OnGetRandomJoke();
    }

    /****
     * Initialize view or any other component required by the view
     */
    private void OnInitialize(){
        jokesViewModel = new ViewModelProvider(this).get(JokesViewModel.class);
    }

    /****
     * Setup Recyclerview
     */
    private void OnSetupRecyclerView(){
        binding.parentRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        binding.parentRecyclerView.setHasFixedSize(true);
        jokeAdapter = new JokeAdapter(getActivity());
    }

    /****
     * Search for random jokes
     * @param keyword
     */
    private void OnSearchJokes(String keyword){
        OnShowProgressBar();

        jokesViewModel.OnSearchJokes(keyword).observe(getViewLifecycleOwner(), jokeResponse -> {
            OnHideProgressBar();

            if(jokeResponse != null){
                binding.total.setText(String.valueOf(jokeResponse.getTotal()));
                jokeArrayList.addAll(jokeResponse.getResult());

                jokeAdapter.submitList(jokeArrayList);
            } else {

                binding.total.setText(String.valueOf(0));
                jokeArrayList.clear();

                /****
                 * Call for a random joke if search list is empty
                 */
                OnGetRandomJoke();
            }

            binding.parentRecyclerView.setAdapter(jokeAdapter);
        });

    }

    /****
     * Get a random joke
     */
    private void OnGetRandomJoke(){
        jokesViewModel.OnGetRandomJoke().observe(getViewLifecycleOwner(), joke -> {
            OnHideProgressBar();
            jokeArrayList.clear();

            if(joke != null){
                binding.total.setText(String.valueOf(1));
                jokeArrayList.add(joke);

                jokeAdapter.submitList(jokeArrayList);
            }

            binding.parentRecyclerView.setAdapter(jokeAdapter);
        });

    }

    /****
     * Search menu item
     */
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.search) {

            SearchView searchView = (SearchView) item.getActionView();
            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    OnSearchJokes(query);
                    return false;
                }

                @Override
                public boolean onQueryTextChange(String newText) {
                    return false;
                }
            });
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /****
     * Hide/Show progressBar
     */
    private void OnShowProgressBar(){
        binding.progressBar.setVisibility(View.VISIBLE);
    }

    private void OnHideProgressBar(){
        binding.progressBar.setVisibility(View.GONE);
    }
}