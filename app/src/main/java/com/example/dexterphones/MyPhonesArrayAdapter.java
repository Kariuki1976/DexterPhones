package com.example.dexterphones;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dexterphones.model.phones.Data;
import com.example.dexterphones.model.phones.Phone;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyPhonesArrayAdapter extends RecyclerView.Adapter<MyPhonesArrayAdapter.PhoneViewHolder> {
    private List<Phone> mDevices;
    private Context mContext;


    public MyPhonesArrayAdapter(Context context, List<Phone> devices) {
        mContext = context;
        mDevices = devices;
    }

    @NonNull
    @Override
    public MyPhonesArrayAdapter.PhoneViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.phone_list_item, parent, false);
        PhoneViewHolder viewHolder = new PhoneViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyPhonesArrayAdapter.PhoneViewHolder holder, int position) {
        holder.bindPhones(mDevices.get(position));
    }

    @Override
    public int getItemCount() {
        return mDevices.size();
    }



    public class PhoneViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.brandName) TextView mBrand;
        @BindView(R.id.phoneName) TextView mPhoneName;
        @BindView(R.id.phoneImage) TextView mImage;
        @BindView(R.id.detail) TextView mDetail;


        public PhoneViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            mContext = itemView.getContext();
        }
        public void bindPhones(Phone device){
            mBrand.setText(device.getBrand());
            mPhoneName.setText(device.getPhoneName());
            mDetail.setText(device.getDetail());
           Picasso.get().load(device.getImage()).into(mImage);
        }

    }
}
