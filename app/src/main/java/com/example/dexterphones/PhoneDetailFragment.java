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
import android.widget.Toast;

import com.example.dexterphones.Constants.Constants;
import com.example.dexterphones.model.phones.Phone;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
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


    public static PhoneDetailFragment newInstance(Phone device) {
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
    @Override
    public void onClick(View v) {
        if (v == mSavePhoneButton) {
            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            String uid = user.getUid();
            DatabaseReference occasionRef = FirebaseDatabase
                    .getInstance()
                    .getReference(Constants.FIREBASE_CHILD_DEVICES)
                    .child(uid);

            DatabaseReference pushRef = occasionRef.push();
            String pushId = pushRef.getKey();
            mDevice.setPushId(pushId);
            pushRef.setValue(mDevice);
            Toast.makeText(getContext(), "Saved", Toast.LENGTH_SHORT).show();
        }
    }
}