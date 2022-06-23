package com.example.dexterphones;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.os.Parcel;

import com.example.dexterphones.Adapters.LatestPagerAdapter;
import com.example.dexterphones.model.latest.Phone;

import org.parceler.Parcels;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LatestPhoneDetailsPager extends AppCompatActivity {
    @BindView(R.id.myViewPager)  ViewPager myViewPager;
    private LatestPagerAdapter latestPagerAdapter;
    List<Phone> mlatest;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_latest_phone_details_pager);
        ButterKnife.bind(this);

        mlatest= Parcels.unwrap(getIntent().getParcelableExtra("latest"));
        int startPos=getIntent().getIntExtra("position",0);

        latestPagerAdapter=new LatestPagerAdapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT,mlatest);
        myViewPager.setAdapter(latestPagerAdapter);
        myViewPager.setCurrentItem(startPos);

    }
}