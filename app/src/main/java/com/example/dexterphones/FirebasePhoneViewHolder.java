package com.example.dexterphones;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.dexterphones.Constants.Constants;
import com.example.dexterphones.model.phones.Phone;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import java.util.ArrayList;

public class FirebasePhoneViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
    View mView;
    Context mContext;

    public FirebasePhoneViewHolder(View itemView) {
        super(itemView);
        mView = itemView;
        mContext = itemView.getContext();
        itemView.setOnClickListener(this);
    }
    public void bindPhones(Phone device) {
        ImageView phoneImageView = (ImageView) mView.findViewById(R.id.phoneImageView);
        TextView phoneNameTextView = (TextView) mView.findViewById(R.id.phoneNameTextView);
        TextView brandNameTextView = (TextView) mView.findViewById(R.id.brandNameTextView);
        TextView detailTextView = (TextView) mView.findViewById(R.id.detailTextView);

        phoneNameTextView.setText(device.getPhoneName());
        brandNameTextView.setText(device.getBrand());
        detailTextView.setText(device.getDetail());

        Picasso.get().load(device.getImage()).into(phoneImageView);
    }
    @Override
    public void onClick(View view) {
        final ArrayList<Phone> devices = new ArrayList<>();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String uid = user.getUid();
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_CHILD_DEVICES).child(uid);
        ref.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    devices.add(snapshot.getValue(Phone.class));
                }

                int itemPosition = getLayoutPosition();

                Intent intent = new Intent(mContext, PhoneDetailACtivity.class);
                intent.putExtra("position", itemPosition + "");
                intent.putExtra("devices", Parcels.wrap(devices));

                mContext.startActivity(intent);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }
}
