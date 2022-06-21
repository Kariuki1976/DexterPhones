package com.example.dexterphones;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.dexterphones.Client.AzharimmClient;
import com.example.dexterphones.Interface.AzharimmAPI;
import com.example.dexterphones.model.phones.Data;
import com.example.dexterphones.model.phones.Phone;
import com.example.dexterphones.model.phones.SearchPhone;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PhoneListActivity extends AppCompatActivity {
    private static final String TAG = PhoneListActivity.class.getSimpleName();

    @BindView(R.id.phoneTextView) TextView mPhoneTextView;
   @BindView(R.id.listView) ListView mListView;
    @BindView(R.id.errorTextView) TextView mErrorTextView;
    @BindView(R.id.progressBar) ProgressBar mProgressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_list);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        String phone = intent.getStringExtra("phone");
        mPhoneTextView.setText("Here are all the phones of type: " + phone);

        AzharimmAPI client = AzharimmClient.getClient();

        Call<SearchPhone> call = client.getDevices(phone);

        call.enqueue(new Callback<SearchPhone>() {
            @Override
            public void onResponse(Call<SearchPhone> call, Response<SearchPhone> response) {
                hideProgressBar();

                if (response.isSuccessful()) {
                    List<Phone> phonesList = response.body().getData().getPhones();
                    String[] devices = new String[phonesList.size()];


                    for (int i = 0; i < devices.length; i++){
                        devices[i] = phonesList.get(i).getPhoneName();
                    }

                    ArrayAdapter adapter
                            = new MyPhonesArrayAdapter(PhoneListActivity.this, android.R.layout.simple_list_item_1, devices);
                    mListView.setAdapter(adapter);

                    showRestaurants();
                } else {
                    showUnsuccessfulMessage();
                }
            }

            @Override
            public void onFailure(Call<SearchPhone> call, Throwable t) {
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

    private void showRestaurants() {
        mListView.setVisibility(View.VISIBLE);
        mPhoneTextView.setVisibility(View.VISIBLE);
    }

    private void hideProgressBar() {
        mProgressBar.setVisibility(View.GONE);
    }
}