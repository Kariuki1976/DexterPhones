package com.example.dexterphones;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

import com.example.dexterphones.ui.BrandsActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    @BindView(R.id.search) TextView mSearch;
    @BindView(R.id.latest) TextView mLatest;
    @BindView(R.id.brands) TextView mBrands;
    @BindView(R.id.cart) TextView mSaved;
    @BindView(R.id.logout) TextView mLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);//will hide the title
        getSupportActionBar().hide(); //hide the title bar
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mSearch.setOnClickListener(this);
        mLatest.setOnClickListener(this);
        mBrands.setOnClickListener(this);
        mSaved.setOnClickListener(this);
        mLogout.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v == mBrands){
            Intent intent = new Intent(MainActivity.this, BrandsActivity.class);
            startActivity(intent);
        }

    }
}
