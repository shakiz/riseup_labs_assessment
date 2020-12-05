package com.client.riseup_labs_assessment.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.client.riseup_labs_assessment.R;
import com.client.riseup_labs_assessment.tools.PrefManager;
import com.client.riseup_labs_assessment.tools.Validation;
import com.client.riseup_labs_assessment.userdb.UserDatabaseHelper;

import static com.client.riseup_labs_assessment.tools.Constants.mIsLoggedIn;
import static com.client.riseup_labs_assessment.tools.Constants.mUserName;

public class LoginActivity extends AppCompatActivity {
    private Validation validation;
    private PrefManager prefManager;
    private Button loginBtn;
    private EditText username, password;
    private TextView registerText;
    private UserDatabaseHelper userDatabaseHelper;

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
        userDatabaseHelper = new UserDatabaseHelper(this);
    }
    //endregion

    //region perform UI interactions
    private void bindUIWithComponents() {
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validation.validateData(username.getText().toString()) && validation.validateData(password.getText().toString())){
                    if (userDatabaseHelper.checkUser(username.getText().toString(),password.getText().toString())){
                        Toast.makeText(getApplicationContext(),"Login Successful",Toast.LENGTH_SHORT).show();
                        prefManager.set(mIsLoggedIn, true);
                        prefManager.set(mUserName, username.getText().toString());
                        startActivity(new Intent(LoginActivity.this, DashboardActivity.class));
                    } else {
                        Toast.makeText(getApplicationContext(),"Login failed",Toast.LENGTH_SHORT).show();
                    }
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        userDatabaseHelper.close();
    }

    //endregion
}