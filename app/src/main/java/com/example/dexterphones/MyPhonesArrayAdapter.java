package com.example.dexterphones;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dexterphones.model.phones.Phone;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

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



    public class PhoneViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        @BindView(R.id.brandNameTextView) TextView mBrand;
        @BindView(R.id.phoneNameTextView) TextView mPhoneName;
        @BindView(R.id.phoneImageView) ImageView mImage;
        @BindView(R.id.detailTextView) TextView mDetail;

        private Context mContext;

        public PhoneViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            mContext = itemView.getContext();
            itemView.setOnClickListener(this);
        }
        public void bindPhones(Phone device){
            mBrand.setText(device.getBrand());
            mPhoneName.setText(device.getPhoneName());
            mDetail.setText(device.getDetail());
           Picasso.get().load(device.getImage()).into(mImage);
        }
        @Override
        public void onClick(View v) {
            int itemPosition = getLayoutPosition();
            Intent intent = new Intent(mContext, PhoneDetailACtivity.class);
            intent.putExtra("position", itemPosition);
            intent.putExtra("devices", Parcels.wrap(mDevices));
            mContext.startActivity(intent);
        }
    }
}
