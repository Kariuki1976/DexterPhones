package com.example.dexterphones.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.dexterphones.fragments.BrandsDetailFragment;
import com.example.dexterphones.model.brands.Datum;

import java.util.List;

public class BrandsPagerAdapter extends FragmentPagerAdapter {
    private List<Datum> mBrands;

    public BrandsPagerAdapter(@NonNull FragmentManager fm, int behavior, List<Datum> brands) {
        super(fm, behavior);
        mBrands = brands;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return BrandsDetailFragment.newInstance(mBrands.get(position));
    }

    @Override
    public int getCount() {
        return mBrands.size();
    }
    @Override
    public CharSequence getPageTitle(int position) {
        return mBrands.get(position).getBrandName();
    }
}
