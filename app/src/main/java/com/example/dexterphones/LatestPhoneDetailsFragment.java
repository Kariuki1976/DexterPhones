package com.example.dexterphones;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dexterphones.model.latest.Phone;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import butterknife.BindView;
import butterknife.ButterKnife;


public class LatestPhoneDetailsFragment extends Fragment implements View.OnClickListener {
    @BindView(R.id.phoneNameTextView)  TextView phoneNameTextView;
    @BindView(R.id.slugTextView)  TextView slugTextView;
    @BindView(R.id.detailsTextView)  TextView detailsTextView;
    @BindView(R.id.phoneImageView)  ImageView phoneImageView;

    private Phone mphone;

    public static LatestPhoneDetailsFragment newInstance(Phone phone){
        LatestPhoneDetailsFragment latestPhoneDetailsFragment=new LatestPhoneDetailsFragment();
        Bundle args = new Bundle();
        args.putParcelable("phone", Parcels.wrap(phone));
        latestPhoneDetailsFragment.setArguments(args);
        return latestPhoneDetailsFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments() !=null){
            mphone=Parcels.unwrap(getArguments().getParcelable("phone"));
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view=inflater.inflate(R.layout.fragment_latest_phone_details, container, false);
        ButterKnife.bind(this,view);
        phoneNameTextView.setText(mphone.getPhoneName());
        slugTextView.setText(mphone.getSlug());
        detailsTextView.setText(mphone.getDetail());
        Picasso.get().load(mphone.getImage()).into(phoneImageView);


        detailsTextView.setOnClickListener(this);
        return  view;


    }


    @Override
    public void onClick(View v) {
      if(v==detailsTextView){
          Intent webIntent=new Intent(Intent.ACTION_VIEW,
                  Uri.parse(mphone.getDetail()));
          startActivity(webIntent);
      }
    }
}