package com.client.riseup_labs_assessment.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.client.riseup_labs_assessment.R;
import com.client.riseup_labs_assessment.tools.PrefManager;
import com.client.riseup_labs_assessment.tools.Validation;

import static com.client.riseup_labs_assessment.tools.Constants.mIsLoggedIn;

public class LoginActivity extends AppCompatActivity {
    private Validation validation;
    private PrefManager prefManager;
    private Button loginBtn;
    private EditText username, password;
    private TextView registerText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //region init components and their interactions
        initUI();
        bindUIWithComponents();
        //endregion
    }

    //region init XML components with backend
    private void initUI() {
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        loginBtn = findViewById(R.id.loginBtn);
        registerText = findViewById(R.id.registerText);
        validation = new Validation(this);
        prefManager = new PrefManager(this);
    }
    //endregion

    //region perform UI interactions
    private void bindUIWithComponents() {
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validation.validateData(username.getText().toString()) && validation.validateData(password.getText().toString())){
                    prefManager.set(mIsLoggedIn, true);
                    startActivity(new Intent(LoginActivity.this, DashboardActivity.class));
                }
                else{
                    Toast.makeText(LoginActivity.this, "Please input correct data", Toast.LENGTH_SHORT).show();
                }
            }
        });

        registerText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, RegistrationActivity.class));
            }
        });
    }
    //endregion

    //region activity components

    @Override
    public void onBackPressed() {
        Intent exitIntent = new Intent(Intent.ACTION_MAIN);
        exitIntent.addCategory(Intent.CATEGORY_HOME);
        exitIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(exitIntent);
    }

    //endregion
}