package com.example.dexterphones;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.GridLayout;

import com.example.dexterphones.Adapters.LatestAdapter;
import com.example.dexterphones.Client.AzharimmClient;
import com.example.dexterphones.Interface.AzharimmAPI;
import com.example.dexterphones.model.latest.Data;
import com.example.dexterphones.model.latest.ListLatest;
import com.example.dexterphones.model.latest.Phone;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListAllLatestActivity extends AppCompatActivity {
    private List<Phone> mlatest;
    // call our api
    AzharimmAPI azharimmAPI;
    LinearLayoutManager linearLayoutManager;
    LatestAdapter latestAdapter;
    Context context;
    GridLayout mainGrid;
    //our recycler
    @BindView(R.id.latestRecyclerView) RecyclerView latestRecyclerView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_all_latest);
        ButterKnife.bind(this);
//        mainGrid=(GridLayout)findViewById(R.id.mainGrid);
//        setSingleEvent(mainGrid);





        azharimmAPI= AzharimmClient.getClient();
        getLatest();

    }

//    private void setSingleEvent(GridLayout mainGrid) {
////        for(int i=0; i<mainGrid.getChildCount(); i++){
////            CardView cardView=(CardView) mainGrid.getChildAt(i);
////            final int finalI=i;
////            cardView.setOnClickListener(new View.OnClickListener() {
////                @Override
////                public void onClick(View v) {
////                    if(finalI==0){
////                        Intent intent= new Intent(ListAllLatestActivity.this, PhoneDetailsActivity.class);
////                        startActivity(intent);
////                    }
////
////
////                }
////            });
////        }
//    }

    private void getLatest() {
        Call<ListLatest>call= azharimmAPI.getLatest();
        call.enqueue(new Callback<ListLatest>() {
            @Override
            public void onResponse(Call<ListLatest> call, Response<ListLatest> response) {
                if(response.isSuccessful()){
                    int status=response.code();
                    List<Phone>latest=response.body().getData().getPhones();
                  //  latestRecyclerView.setAdapter(new LatestAdapter());
                    latestAdapter=new LatestAdapter(ListAllLatestActivity.this,latest);
                    latestRecyclerView.setAdapter(latestAdapter);
                    RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(ListAllLatestActivity.this);
                    latestRecyclerView.setLayoutManager(layoutManager);

                    

                }
            }

            @Override
            public void onFailure(Call<ListLatest> call, Throwable t) {
               Log.e(TAG, "Faaaaaaaailed To GEEEEEEET CONNECTION TO API"+t.getMessage());
            }
        });
            }
}