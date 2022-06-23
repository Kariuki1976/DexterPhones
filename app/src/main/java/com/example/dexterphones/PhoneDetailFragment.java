package com.example.dexterphones;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dexterphones.model.phones.Phone;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import butterknife.BindView;
import butterknife.ButterKnife;


public class PhoneDetailFragment extends Fragment implements View.OnClickListener{
    @BindView(R.id.phoneImageView) ImageView mImageLabel;
    @BindView(R.id.phoneNameTextView) TextView mPhoneNameLabel;
    @BindView(R.id.brandNameTextView) TextView mBrandNameLabel;
    @BindView(R.id.websiteTextView) TextView mWebsiteLabel;
    @BindView(R.id.savePhoneButton) Button mSavePhoneButton;

    private Phone mDevice;

    public PhoneDetailFragment() {

    }


    public static PhoneDetailFragment newInstance(Phone, device) {
        PhoneDetailFragment phoneDetailFragment = new PhoneDetailFragment();
        Bundle args = new Bundle();
        args.putParcelable("device", Parcels.wrap(device));
        phoneDetailFragment.setArguments(args);
        return phoneDetailFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        assert getArguments() != null;
        mDevice = Parcels.unwrap(getArguments().getParcelable("device"));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Inflate the layout for this fragment

        View view =  inflater.inflate(R.layout.fragment_phone_detail, container, false);
        ButterKnife.bind(this, view);
        Picasso.get().load(mDevice.getImage()).into(mImageLabel);


        mPhoneNameLabel.setText(mDevice.getPhoneName());
        mBrandNameLabel.setText(mDevice.getBrand());
        mWebsiteLabel.setText(mDevice.getDetail());

        mSavePhoneButton.setOnClickListener(this);

        return view;
    }
}