package com.example.jokes.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.jokes.R;
import com.example.jokes.entities.Joke;

import de.hdodenhof.circleimageview.CircleImageView;

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
        private CircleImageView imageView;
        private TextView joke;

        public ItemHolder(@NonNull View itemView) {
            super(itemView);

            joke = itemView.findViewById(R.id.joke);
            imageView = itemView.findViewById(R.id.image);

            childRecyclerView = itemView.findViewById(R.id.childRecyclerView);
            childRecyclerView.setLayoutManager(new LinearLayoutManager(context));
            childRecyclerView.setHasFixedSize(true);
        }
    }

    @NonNull
    @Override
    public JokeAdapter.ItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.joke_item, parent, false);

        return new ItemHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull JokeAdapter.ItemHolder holder, int position) {
        final Joke jokes = getItem(position);

        /****
         * Bind widgets with data
         */
        holder.joke.setText(jokes.getValue());

        /****
         * Load image from the internet
         */
        try{
            Glide.with(context)
                    .load(jokes.getIcon_url())
                    .centerCrop()
                    .placeholder(R.drawable.ic_launcher_background)
                    .into(holder.imageView);
        } catch (Exception e){
            e.printStackTrace();
        }

        /****
         * Connect the second Adapter that loads categories
         */
        CategoryAdapter categoryAdapter = new CategoryAdapter(context);

        if(jokes.getCategoriesList() != null) {
            categoryAdapter.submitList(jokes.getCategoriesList());
            holder.childRecyclerView.setAdapter(categoryAdapter);
        }
    }
}
