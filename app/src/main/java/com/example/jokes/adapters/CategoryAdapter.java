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
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jokes.R;

public class CategoryAdapter extends ListAdapter<String, CategoryAdapter.ItemHolder> {
    private Context context;

    public CategoryAdapter(Context context){
        super(DIFF_CALLBACK);
        this.context = context;
    }

    private static final DiffUtil.ItemCallback<String> DIFF_CALLBACK = new DiffUtil.ItemCallback<String>() {
        @Override
        public boolean areItemsTheSame(@NonNull String oldItem, @NonNull String newItem) {
            return oldItem.equals(newItem);
        }

        @Override
        public boolean areContentsTheSame(@NonNull String oldItem, @NonNull String newItem) {
            return oldItem.equals(newItem);
        }
    };

    class ItemHolder extends RecyclerView.ViewHolder {
        private TextView category;

        public ItemHolder(@NonNull View itemView) {
            super(itemView);

            category = itemView.findViewById(R.id.category);
        }
    }

    @NonNull
    @Override
    public CategoryAdapter.ItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_item, parent, false);
        view.setAnimation(AnimationUtils.loadAnimation(context, R.anim.fade));

        return new CategoryAdapter.ItemHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryAdapter.ItemHolder holder, int position) {
        final String category = getItem(position);

        Log.d("DS", category);

        if(category != null) {

            holder.category.setText(category);
        }
    }
}
