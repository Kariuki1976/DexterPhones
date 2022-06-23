package com.example.dexterphones.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dexterphones.R;
import com.example.dexterphones.model.brands.Datum;
import com.example.dexterphones.model.latest.Phone;
import com.example.dexterphones.ui.BrandsDetailActivity;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BrandsAdapter extends RecyclerView.Adapter<BrandsAdapter.BrandsViewHolder> {

    private List<Datum> mBrands;
    private Context mContext;


    public BrandsAdapter(Context context, List<Datum> brands) {
        mContext = context;
        mBrands = brands;
    }
    public void setFilteredList(List<Datum> filteredList){
        this.mBrands = filteredList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public BrandsAdapter.BrandsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.displaybrands, parent, false);
        BrandsViewHolder viewHolder = new BrandsViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull BrandsAdapter.BrandsViewHolder holder, int position) {
        holder.bindBrands(mBrands.get(position));
    }

    @Override
    public int getItemCount() {
        return mBrands.size();
    }



    public class BrandsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.brandName)
        TextView mBrand;
        @BindView(R.id.slug)
        TextView mSlug;
        @BindView(R.id.count)
        TextView mCount;
        @BindView(R.id.detail)
        TextView mDetail;


        public BrandsViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            mContext = itemView.getContext();
            itemView.setOnClickListener(this);
        }

        public void bindBrands(Datum datum) {
            mBrand.setText(datum.getBrandName());
            mSlug.setText(datum.getBrandSlug());
            mCount.setText(datum.getDeviceCount().toString());
            mDetail.setText(datum.getDetail());

//           Picasso.get().load(phone.getImage()).into(mImage);
        }

        @Override
        public void onClick(View v) {
            int itemPosition = getLayoutPosition();
            Intent intent = new Intent(mContext, BrandsDetailActivity.class);
            intent.putExtra("position", itemPosition);
            intent.putExtra("brands", Parcels.wrap(mBrands));
            mContext.startActivity(intent);

        }
    }
}
