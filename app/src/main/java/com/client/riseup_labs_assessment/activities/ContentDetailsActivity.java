package com.client.riseup_labs_assessment.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

import com.client.riseup_labs_assessment.R;
import com.client.riseup_labs_assessment.models.contents.Content;

public class ContentDetailsActivity extends AppCompatActivity {
    private Content content;
    private SeekBar ratingBar;
    private TextView name, language, type, status, time, days;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content_details);

        //region get intent data
        getIntentData();
        //endregion

        //region init UI and perform their interactions
        initUI();
        bindUiWithComponents();
        //endregion
    }

    //region get intent data
    private void getIntentData(){
        if (getIntent().getExtras().getParcelable("content") != null){
            content = getIntent().getExtras().getParcelable("content");
        }
    }
    //endregion

    //region init UI
    private void initUI() {
        name = findViewById(R.id.name);
        language = findViewById(R.id.language);
        type = findViewById(R.id.type);
        status = findViewById(R.id.status);
        ratingBar = findViewById(R.id.ratingBar);
        time = findViewById(R.id.time);
        days = findViewById(R.id.days);
    }
    //endregion

    //region perform UI interactions
    private void bindUiWithComponents() {
        findViewById(R.id.BackButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ContentDetailsActivity.this, DashboardActivity.class));
            }
        });

        //region set content details
        setContentDetails();
        //endregion
    }
    //endregion

    //region set content details
    private void setContentDetails(){
        if (content != null){
            name.setText("Show Name : "+content.getName());
            language.setText("Show Language : "+content.getLanguage());
            type.setText("Show Type : "+content.getType());
            status.setText("Show "+content.getStatus());
            if (content.getRating() != null) {
                if (content.getRating().getAverage() != null){
                    if (content.getRating().getAverage() > 0){
                        ratingBar.setProgress(content.getRating().getAverage());
                    }
                }
            }
            if (content.getSchedule() != null) {
                if (content.getSchedule().getTime() != null){
                    time.setText(content.getSchedule().getTime());
                }
                if (content.getSchedule().getDays() != null){
                    days.setText(content.getSchedule().getDays().toString());
                }
            }
        }
    }
    //endregion

    //region activity components
    @Override
    public void onBackPressed() {
        startActivity(new Intent(ContentDetailsActivity.this,DashboardActivity.class));
    }
    //endregion
}