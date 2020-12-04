package com.client.riseup_labs_assessment.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.client.riseup_labs_assessment.R;

public class ProfileActivity extends AppCompatActivity {

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
    }
    //endregion
}