package com.example.dexterphones.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.dexterphones.Client.AzharimmClient;
import com.example.dexterphones.Interface.AzharimmAPI;
import com.example.dexterphones.R;
import com.example.dexterphones.adapters.BrandsAdapter;
import com.example.dexterphones.model.brands.Datum;
import com.example.dexterphones.model.brands.ListBrands;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BrandsActivity extends AppCompatActivity {
    @BindView(R.id.recyclerView) RecyclerView mRecyclerView;
    @BindView(R.id.errorTextView)
    TextView mErrorTextView;
    @BindView(R.id.progressBar)
    ProgressBar mProgressBar;
    private BrandsAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brands);
        ButterKnife.bind(this);

        //consuming the API
        AzharimmAPI client = AzharimmClient.getClient();
        Call<ListBrands> call = client.getBrands();

        call.enqueue(new Callback<ListBrands>() {
            @Override
            public void onResponse(Call<ListBrands> call, Response<ListBrands> response) {
                hideProgressBar();

                if (response.isSuccessful()) {
                    List<Datum> brands = response.body().getData();
                    mAdapter = new BrandsAdapter(BrandsActivity.this, brands);
                    mRecyclerView.setAdapter(mAdapter);
                    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(BrandsActivity.this);
                    mRecyclerView.setLayoutManager(layoutManager);
                    mRecyclerView.setHasFixedSize(true);

                    showBrands();
                } else {
                    showUnsuccessfulMessage();
                }
            }
            @Override
            public void onFailure(Call<ListBrands> call, Throwable t) {
                hideProgressBar();
                showFailureMessage();
            }
        });
    }
    private void showFailureMessage() {
        mErrorTextView.setText("Something went wrong. Please check your Internet connection and try again later");
        mErrorTextView.setVisibility(View.VISIBLE);
    }

    private void showUnsuccessfulMessage() {
        mErrorTextView.setText("Something went wrong. Please try again later");
        mErrorTextView.setVisibility(View.VISIBLE);
    }

    private void showBrands() {
        mRecyclerView.setVisibility(View.VISIBLE);
    }

    private void hideProgressBar() {
        mProgressBar.setVisibility(View.GONE);
    }
}