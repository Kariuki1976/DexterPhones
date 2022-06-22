package com.example.dexterphones.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.example.dexterphones.R;
import com.example.dexterphones.adapters.BrandsPagerAdapter;
import com.example.dexterphones.model.brands.Datum;

import org.parceler.Parcels;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BrandsDetailActivity extends AppCompatActivity {
    @BindView(R.id.viewPager) ViewPager mViewPager;
    private BrandsPagerAdapter adapterViewPager;
    List<Datum> mBrands;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brands_detail);
        ButterKnife.bind(this);

        mBrands = Parcels.unwrap(getIntent().getParcelableExtra("brands"));
        int startingPosition = getIntent().getIntExtra("position", 0);

        adapterViewPager = new BrandsPagerAdapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT, mBrands);
        mViewPager.setAdapter(adapterViewPager);
        mViewPager.setCurrentItem(startingPosition);
    }

}