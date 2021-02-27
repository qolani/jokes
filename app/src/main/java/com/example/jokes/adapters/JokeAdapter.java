package com.example.jokes.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jokes.R;
import com.example.jokes.entities.Joke;

public class JokeAdapter extends ListAdapter<Joke, JokeAdapter.ItemHolder> {
    private Context context;

    public JokeAdapter(Context context){
        super(DIFF_CALLBACK);
        this.context = context;
    }

    private static final DiffUtil.ItemCallback<Joke> DIFF_CALLBACK = new DiffUtil.ItemCallback<Joke>() {
        @Override
        public boolean areItemsTheSame(@NonNull Joke oldItem, @NonNull Joke newItem) {
            return oldItem.getJokeId().equals(newItem.getJokeId());
        }

        @Override
        public boolean areContentsTheSame(@NonNull Joke oldItem, @NonNull Joke newItem) {
            return oldItem.getValue().equals(newItem.getValue());
        }
    };

    class ItemHolder extends RecyclerView.ViewHolder {
        private RecyclerView childRecyclerView;
        private TextView joke;

        public ItemHolder(@NonNull View itemView) {
            super(itemView);

            joke = itemView.findViewById(R.id.joke);
            childRecyclerView = itemView.findViewById(R.id.childRecyclerView);
            childRecyclerView.setLayoutManager(new LinearLayoutManager(context));
            childRecyclerView.setHasFixedSize(true);
        }
    }

    @NonNull
    @Override
    public JokeAdapter.ItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.joke_item, parent, false);
        view.setAnimation(AnimationUtils.loadAnimation(context, R.anim.fade));

        return new ItemHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull JokeAdapter.ItemHolder holder, int position) {
        final Joke jokes = getItem(position);

        holder.joke.setText(jokes.getValue());

        CategoryAdapter categoryAdapter = new CategoryAdapter(context);

        categoryAdapter.submitList(jokes.getCategoriesList());
        holder.childRecyclerView.setAdapter(categoryAdapter);

    }
}
