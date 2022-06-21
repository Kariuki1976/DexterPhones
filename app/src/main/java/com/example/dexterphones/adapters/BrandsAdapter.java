package com.example.dexterphones.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dexterphones.R;
import com.example.dexterphones.model.brands.Datum;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BrandsAdapter extends RecyclerView.Adapter<BrandsAdapter.BrandsViewHolder>{

    private List<Datum> mRbrands;
    private Context mContext;

    public BrandsAdapter(Context context, List<Datum> brands) {
        mContext = context;
        mRbrands = brands;
    }

    @NonNull
    @Override
    public BrandsAdapter.BrandsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull BrandsAdapter.BrandsViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class BrandsViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.brandName) TextView mBrand;
        @BindView(R.id.slug) TextView mSlug;
        @BindView(R.id.count) TextView mCount;
        @BindView(R.id.detail) TextView mDetail;

        public BrandsViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            mContext = itemView.getContext();
        }
    }
}