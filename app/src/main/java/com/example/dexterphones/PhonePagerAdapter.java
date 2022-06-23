package com.example.dexterphones;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.dexterphones.model.phones.Phone;

import java.util.List;

public class PhonePagerAdapter extends FragmentPagerAdapter {
    private List<Phone> mDevices;

    public PhonePagerAdapter(@NonNull FragmentManager fm, int behavior, List<Phone> devices) {
        super(fm, behavior);
        mDevices = devices;
    }

    @Override
    public Fragment getItem(int position) {
        return PhoneDetailFragment.newInstance(mDevices.get(position));
    }

    @Override
    public int getCount() {
        return mDevices.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mDevices.get(position).getPhoneName();
    }
}
