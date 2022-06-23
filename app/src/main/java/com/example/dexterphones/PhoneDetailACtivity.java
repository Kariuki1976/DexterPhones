package com.example.dexterphones;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.example.dexterphones.model.phones.Phone;

import org.parceler.Parcels;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PhoneDetailACtivity extends AppCompatActivity {
    @BindView(R.id.viewPager)
    ViewPager mViewPager;
    private PhonePagerAdapter adapterViewPager;
    List<Phone> mDevices;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_detail);
        ButterKnife.bind(this);

        mDevices = Parcels.unwrap(getIntent().getParcelableExtra("devices"));
        int startingPosition = getIntent().getIntExtra("position", 0);

        adapterViewPager = new PhonePagerAdapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT, mDevices);
        mViewPager.setAdapter(adapterViewPager);
        mViewPager.setCurrentItem(startingPosition);
    }
}