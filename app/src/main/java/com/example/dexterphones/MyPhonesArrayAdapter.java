package com.example.dexterphones;

import android.content.Context;
import android.widget.ArrayAdapter;

public class MyPhonesArrayAdapter extends ArrayAdapter {
    private Context mContext;
    private String[] mPhones;


    public MyPhonesArrayAdapter(Context mContext, int resource, String[] mPhones) {
        super(mContext, resource);
        this.mContext = mContext;
        this.mPhones = mPhones;

    }

    @Override
    public Object getItem(int position) {
        String phone = mPhones[position];
        return String.format("%s \nServes great: %s", phone);
    }

    @Override
    public int getCount() {
        return mPhones.length;
    }
}
