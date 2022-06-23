package com.example.dexterphones.Adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.dexterphones.LatestPhoneDetailsFragment;
import com.example.dexterphones.model.latest.Phone;

import java.util.List;

public class LatestPagerAdapter extends FragmentPagerAdapter {
    private List<Phone> mlatest;

    public LatestPagerAdapter(@NonNull FragmentManager fm, int behavior, List<Phone> latest) {
        super(fm, behavior);
        mlatest = latest;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return LatestPhoneDetailsFragment.newInstance(mlatest.get(position));
    }

    @Override
    public int getCount() {
        return mlatest.size();
    }


    @Override
    public CharSequence getPageTitle(int position) {
        return mlatest.get(position).getPhoneName();
    }
}