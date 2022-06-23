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

import com.example.dexterphones.LatestPhoneDetailsFragment;
import com.example.dexterphones.LatestPhoneDetailsPager;
import com.example.dexterphones.R;
import com.example.dexterphones.SearchActivity;
import com.example.dexterphones.model.latest.Phone;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LatestAdapter  extends RecyclerView.Adapter<LatestAdapter.itemViewHolder> {
    private Context context;
    private List<Phone> mlatest;


    // constructor
    public LatestAdapter(Context context, List<Phone> latest) {
        this.context = context;
        this.mlatest=latest;
    }

    @NonNull
    @Override
    public LatestAdapter.itemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // inflate our item
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.latest_items,parent,false);
        itemViewHolder viewHolder=new itemViewHolder(view);
        return viewHolder;
    }



    @Override
    public void onBindViewHolder(@NonNull itemViewHolder holder, int position) {
        holder.bindLatest(mlatest.get(position));

    }

    @Override
    public int getItemCount() {

        return mlatest.size();
    }

    public class itemViewHolder extends RecyclerView.ViewHolder  implements View.OnClickListener{
        @BindView(R.id.latestImageView)  ImageView latestImageView;
        @BindView(R.id.latestTextView)  TextView latestTextView;


        private Context context;
        public itemViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            context=itemView.getContext();
            itemView.setOnClickListener(this);


        }

        public void bindLatest(Phone myPhone){
            latestTextView.setText(myPhone.getPhoneName());
            Picasso.get().load(myPhone.getImage()).into(latestImageView);

        }

        @Override
        public void onClick(View v) {
            int itemPosition = getLayoutPosition();
            Intent intent = new Intent(context, LatestPhoneDetailsPager.class);
            intent.putExtra("position", itemPosition);
            intent.putExtra("latest", Parcels.wrap(mlatest));
            context.startActivity(intent);

        }
    }
}
