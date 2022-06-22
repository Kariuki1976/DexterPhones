package com.example.dexterphones.fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.dexterphones.R;
import com.example.dexterphones.model.brands.Datum;

import org.parceler.Parcels;

import butterknife.BindView;
import butterknife.ButterKnife;


public class BrandsDetailFragment extends Fragment implements View.OnClickListener {

    @BindView(R.id.brandName)
    TextView mBrand;
    @BindView(R.id.slug)
    TextView mSlug;
    @BindView(R.id.count)
    TextView mCount;
    @BindView(R.id.detail)
    TextView mDetail;

    private Datum mData;

    public BrandsDetailFragment() {
        // Required empty public constructor
    }


    public static BrandsDetailFragment newInstance(Datum brand) {
        BrandsDetailFragment fragment = new BrandsDetailFragment();
        Bundle args = new Bundle();
        args.putParcelable("brand", Parcels.wrap(brand));
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mData = Parcels.unwrap(getArguments().getParcelable("brand"));
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_brands_detail, container, false);
        ButterKnife.bind(this, view);

        mBrand.setText(mData.getBrandName());
        mSlug.setText(mData.getBrandSlug());
        mCount.setText(mData.getDeviceCount().toString());
        mDetail.setText(mData.getDetail());
        mDetail.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        if (v == mDetail) {
            Intent webIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(mData.getBrandSlug()));
            startActivity(webIntent);

        }
    }
}