package com.example.dexterphones.Adapters;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class LatestAdapter  extends RecyclerView.Adapter<LatestAdapter.itemViewHolder> {


    @NonNull
    @Override
    public itemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull itemViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class itemViewHolder extends RecyclerView.ViewHolder {

        public itemViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
