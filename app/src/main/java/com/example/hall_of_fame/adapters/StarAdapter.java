package com.example.hall_of_fame.adapters;

import android.animation.LayoutTransition;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.transition.AutoTransition;
import androidx.transition.TransitionManager;

import com.example.hall_of_fame.Star;
import com.example.hall_of_fame.databinding.ItemStarBinding;

import java.util.List;

public class StarAdapter extends RecyclerView.Adapter<StarAdapter.StarHolder> {
    List<Star> stars;
    static StarAdapter adapter;
    private int positionExpanded = -1;


    public StarAdapter(List<Star> stars) {
        this.stars = stars;
        adapter = this;
    }

    @NonNull
    @Override
    public StarHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ItemStarBinding binding = ItemStarBinding.inflate(inflater, parent, false);
        return new StarHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull StarHolder holder, int position) {
        holder.bind(stars.get(position));

        // show/hide detail view when card expanded/collapsed
        final boolean expanded = position == positionExpanded;
        holder.binding.aboutView.setVisibility(expanded ? View.VISIBLE : View.GONE);
        holder.itemView.setActivated(expanded);

        // change header size when card expanded/collapsed
        holder.binding.tvName.setTextSize(TypedValue.COMPLEX_UNIT_SP, expanded ? 24 : 18);
        holder.binding.tvMajor.setTextSize(TypedValue.COMPLEX_UNIT_SP, expanded ? 16 : 12);

        // add default changing animation on expansion toggle
        ViewGroup layout = (ViewGroup) holder.binding.getRoot();
        LayoutTransition layoutTransition = layout.getLayoutTransition();
        layoutTransition.enableTransitionType(LayoutTransition.CHANGING);

        // expand/collapse card on click
        holder.itemView.setOnClickListener(v -> {
            positionExpanded = expanded ? -1 : position;
            notifyDataSetChanged();
        });
    }

    @Override
    public int getItemCount() {
        return stars.size();
    }


    protected static class StarHolder extends RecyclerView.ViewHolder {
        final ItemStarBinding binding;

        public StarHolder(ItemStarBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(Star star) {
            binding.setStar(star);
            binding.executePendingBindings();
        }
    }
}

