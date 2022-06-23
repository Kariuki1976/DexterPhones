package com.example.dexterphones.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dexterphones.MainActivity;
import com.example.dexterphones.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SignupActivity extends AppCompatActivity implements View.OnClickListener{
    public static final String TAG = SignupActivity.class.getSimpleName();
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    @BindView(R.id.signup_signup_btn) Button mCreateUserButton;
    @BindView(R.id.signup_name_edit_text) EditText mNameEditText;
    @BindView(R.id.signup_email_edit_text) EditText mEmailEditText;
    @BindView(R.id.signup_password_edit_text) EditText mPasswordEditText;
    @BindView(R.id.signup_cnf_password_edit_text) EditText mConfirmPasswordEditText;
    @BindView(R.id.signup_login_text_view) TextView mLoginTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        requestWindowFeature(Window.FEATURE_NO_TITLE);//will hide the title
//        getSupportActionBar().hide(); //hide the title bar
        setContentView(R.layout.activity_signup);

        ButterKnife.bind(this);

        mAuth = FirebaseAuth.getInstance();

        mLoginTextView.setOnClickListener(this);
        mCreateUserButton.setOnClickListener(this);

        createAuthStateListener();
        
    }
    @Override
    public void onClick(View view) {
        if (view == mLoginTextView) {
            Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
        }

        if (view == mCreateUserButton) {
            createNewUser();
        }
    }

    private void createNewUser() {
        final String name = mNameEditText.getText().toString().trim();
        final String email = mEmailEditText.getText().toString().trim();
        String password = mPasswordEditText.getText().toString().trim();
        String confirmPassword = mConfirmPasswordEditText.getText().toString().trim();

        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, task -> {
            if (task.isSuccessful()){
                Log.d(TAG, "Authentication successful");
            }else {
                Toast.makeText(SignupActivity.this,"Authentication failed.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void createAuthStateListener(){
        mAuthListener = firebaseAuth -> {
            final FirebaseUser user = firebaseAuth.getCurrentUser();
            if (user != null){
                Intent intent = new Intent(SignupActivity.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                finish();
            }
        };
    }

    @Override
    public void onStart(){
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop(){
        super.onStop();
        if(mAuthListener != null){
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }
}