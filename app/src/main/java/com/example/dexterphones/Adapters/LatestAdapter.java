package com.example.dexterphones.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.dexterphones.R;
import com.example.dexterphones.SearchActivity;
import com.example.dexterphones.model.latest.Data;
import com.example.dexterphones.model.latest.Phone;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LatestAdapter  extends RecyclerView.Adapter<LatestAdapter.itemViewHolder> {
    private Context context;
    private List<Phone> mlatest;


    // constructor
    public LatestAdapter(Context context, List<Phone>mlatest) {
        this.context = context;
        this.mlatest=mlatest;
    }

    @NonNull
    @Override
    public itemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // inflate our item
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.latest_items,parent,false);
        itemViewHolder viewHolder=new itemViewHolder(view);
        return viewHolder;
    }

   public void filterList(List<Phone>filterList){
        mlatest=filterList;
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(@NonNull itemViewHolder holder, int position) {
        holder.bindLatest(mlatest.get(position));

        holder.itemView.setOnClickListener(v->{
            Intent intent = new Intent(context, SearchActivity.class);
//            intent.putExtra("phone",holder.bindLatest(mlatest.);)
        });
    }

    @Override
    public int getItemCount() {

        return mlatest.size();
    }

    public class itemViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.latestImageView)  ImageView latestImageView;
        @BindView(R.id.latestTextView)  TextView latestTextView;


        public itemViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            context=itemView.getContext();


        }

        public void bindLatest(Phone phone){
            latestTextView.setText(phone.getPhoneName());
            Picasso.get().load(phone.getImage()).into(latestImageView);

        }
    }
}
