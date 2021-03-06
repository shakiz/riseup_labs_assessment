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
import com.client.riseup_labs_assessment.models.User;
import com.client.riseup_labs_assessment.tools.PrefManager;
import com.client.riseup_labs_assessment.tools.Validation;
import com.client.riseup_labs_assessment.userdb.UserDatabaseHelper;

import static com.client.riseup_labs_assessment.tools.Constants.mIsLoggedIn;

public class RegistrationActivity extends AppCompatActivity {
    private Validation validation;
    private PrefManager prefManager;
    private Button loginBtn;
    private EditText name, username, password, phone;
    private TextView returnToLogin;
    private UserDatabaseHelper userDatabaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        //region init components and their interactions
        initUI();
        bindUIWithComponents();
        //endregion
    }

    //region init XML components with backend
    private void initUI() {
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        name = findViewById(R.id.name);
        phone = findViewById(R.id.phone);
        returnToLogin = findViewById(R.id.returnToLogin);
        loginBtn = findViewById(R.id.loginBtn);
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
                if (validation.validateData(username.getText().toString()) && validation.validateData(password.getText().toString()) &&
                        validation.validateData(name.getText().toString()) && validation.validateData(phone.getText().toString())){
                    if (userDatabaseHelper.checkUserExistence(username.getText().toString())){
                        Toast.makeText(RegistrationActivity.this, "User already exists, Please try with different username", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Toast.makeText(RegistrationActivity.this, "Registration Successful", Toast.LENGTH_SHORT).show();
                        prefManager.set(mIsLoggedIn, true);
                        User user = new User(name.getText().toString(),username.getText().toString(),Integer.parseInt(password.getText().toString()),phone.getText().toString());
                        userDatabaseHelper.addUser(user);
                        startActivity(new Intent(RegistrationActivity.this, LoginActivity.class));
                    }
                }
                else{
                    Toast.makeText(RegistrationActivity.this, "Please input correct data", Toast.LENGTH_SHORT).show();
                }
            }
        });

        returnToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegistrationActivity.this, LoginActivity.class));
            }
        });
    }
    //endregion

    //region activity components

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        userDatabaseHelper.close();
    }

    //endregion
}