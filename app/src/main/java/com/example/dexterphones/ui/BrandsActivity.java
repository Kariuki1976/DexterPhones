package com.example.dexterphones.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dexterphones.Client.AzharimmClient;
import com.example.dexterphones.Interface.AzharimmAPI;
import com.example.dexterphones.R;
import com.example.dexterphones.adapters.BrandsAdapter;
import com.example.dexterphones.model.brands.Datum;
import com.example.dexterphones.model.brands.ListBrands;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BrandsActivity extends AppCompatActivity {
    @BindView(R.id.recyclerView) RecyclerView mRecyclerView;
    @BindView(R.id.errorTextView) TextView mErrorTextView;
    @BindView(R.id.progressBar) ProgressBar mProgressBar;
    @BindView(R.id.searchview) SearchView msearch;
    private BrandsAdapter mAdapter;
    private List<Datum> mBrands;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        requestWindowFeature(Window.FEATURE_NO_TITLE);//will hide the title
//        getSupportActionBar().hide(); //hide the title bar
        setContentView(R.layout.activity_brands);
        ButterKnife.bind(this);

        mBrands = new ArrayList<>();

        msearch.clearFocus();
        msearch.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
//                filterList(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filterList(newText);
                return true;
            }
            private void filterList(String text) {
                List<Datum> filteredList = new ArrayList<>();
                for(Datum datum: mBrands){
                    if(datum.getBrandName().toLowerCase().contains(text.toLowerCase().trim())){
                        filteredList.add(datum);
                    }
                }
                if(filteredList.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Brand not found", Toast.LENGTH_LONG).show();
                }
                else {
                    mAdapter.setFilteredList(filteredList);
                }
            }
        });

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