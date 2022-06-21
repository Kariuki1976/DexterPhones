package com.example.dexterphones;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    @BindView(R.id.findPhonesButton)
    Button mFindPhonesButton;
    @BindView(R.id.phoneEditText)
    EditText mPhoneEditText;
    @BindView(R.id.appNameTextView)
    TextView mAppNameTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mFindPhonesButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v){
        if (v == mFindPhonesButton) {
            String phone = mPhoneEditText.getText().toString();
            Intent intent = new Intent(MainActivity.this, PhoneListActivity.class);
            intent.putExtra("phone", phone);
            startActivity(intent);
        }
    }
}