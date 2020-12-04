package com.client.riseup_labs_assessment.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ClipData;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.client.riseup_labs_assessment.R;
import com.client.riseup_labs_assessment.adapters.ContentAdapter;
import com.client.riseup_labs_assessment.models.contents.Content;
import com.client.riseup_labs_assessment.viewmodels.ContentViewModel;

import java.util.ArrayList;
import java.util.List;

public class DashboardActivity extends AppCompatActivity {
    private ContentViewModel contentViewModel;
    private Content contentList;
    private RecyclerView contentRecycler;
    private ContentAdapter contentAdapter;
    private EditText searchText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        //region init components and their interactions
        initUI();
        bindUIWithComponents();
        //endregion
    }

    //region init components
    private void initUI() {
        searchText = findViewById(R.id.searchText);
        contentRecycler = findViewById(R.id.contentRecycler);
        contentViewModel = ViewModelProviders.of(this).get(ContentViewModel.class);
    }
    //endregion

    //region UI interactions
    private void bindUIWithComponents() {
        //region get server data
        findViewById(R.id.Search).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!TextUtils.isEmpty(searchText.getText().toString())){
                    performServerOperation(searchText.getText().toString());
                }
            }
        });
        //endregion

        findViewById(R.id.BackButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent exitIntent = new Intent(Intent.ACTION_MAIN);
                exitIntent.addCategory(Intent.CATEGORY_HOME);
                exitIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(exitIntent);
            }
        });

        findViewById(R.id.tapToViewProfile).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DashboardActivity.this, ProfileActivity.class));
            }
        });
    }
    //endregion

    //region perform mvvm server fetch
    private void performServerOperation(String queryString){
        contentViewModel.getData(queryString);
        contentViewModel.getContentList().observe(this, new Observer<Content>() {
            @Override
            public void onChanged(Content contents) {
                if (contents != null) {
                    contentList = contents;
                    loadListView();
                    contentAdapter.notifyDataSetChanged();
                }
            }
        });
    }
    //endregion

    //region set recycler adapter
    private void loadListView(){
        contentAdapter = new ContentAdapter(contentList);
        contentRecycler.setLayoutManager(new LinearLayoutManager(this));
        contentRecycler.setAdapter(contentAdapter);
    }
    //endregion
}