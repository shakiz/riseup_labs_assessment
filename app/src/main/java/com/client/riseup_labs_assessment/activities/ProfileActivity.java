package com.client.riseup_labs_assessment.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.client.riseup_labs_assessment.R;
import com.client.riseup_labs_assessment.models.User;
import com.client.riseup_labs_assessment.tools.PrefManager;
import com.client.riseup_labs_assessment.userdb.UserDatabaseHelper;

import static com.client.riseup_labs_assessment.tools.Constants.mUserName;

public class ProfileActivity extends AppCompatActivity {
    private TextView username, name, phone;
    private User user;
    private UserDatabaseHelper userDatabaseHelper;
    private PrefManager prefManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        //region init components and their interactions
        initUI();
        bindUIWithComponents();
        //endregion
    }

    //region init components
    private void initUI() {
        username = findViewById(R.id.username);
        name = findViewById(R.id.name);
        phone = findViewById(R.id.phone);
        userDatabaseHelper = new UserDatabaseHelper(this);
        prefManager = new PrefManager(this);
    }
    //endregion

    //region UI interactions
    private void bindUIWithComponents() {
        findViewById(R.id.BackButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ProfileActivity.this, DashboardActivity.class));
            }
        });

        //region get user data from db
        getUserDetails();
        //endregion

        //region set user data
        setUserDetails();
        //endregion
    }
    //endregion

    private void setUserDetails() {
        if (user != null){
            name.setText("Name : "+user.getName());
            username.setText("@"+user.getUsername());
            phone.setText(user.getMobileNo());
        }
    }

    private void getUserDetails() {
        user = userDatabaseHelper.getUser(prefManager.getString(mUserName));
    }

    //region activity components

    @Override
    protected void onDestroy() {
        super.onDestroy();
        userDatabaseHelper.close();
    }

    //endregion
}