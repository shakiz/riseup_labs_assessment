package com.client.riseup_labs_assessment.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.client.riseup_labs_assessment.R;
import com.client.riseup_labs_assessment.adapters.ContentAdapter;
import com.client.riseup_labs_assessment.models.contents.Content;
import com.client.riseup_labs_assessment.tools.PrefManager;
import com.client.riseup_labs_assessment.viewmodels.ContentViewModel;

import static com.client.riseup_labs_assessment.tools.Constants.mIsLoggedIn;

public class DashboardActivity extends AppCompatActivity {
    private ContentViewModel contentViewModel;
    private Content content;
    private RecyclerView contentRecycler;
    private ContentAdapter contentAdapter;
    private EditText searchText;
    private ProgressBar progressBar;
    private TextView searchResultAppear;
    private PrefManager prefManager;

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
        progressBar = findViewById(R.id.progress);
        searchResultAppear = findViewById(R.id.searchResultAppear);
        prefManager = new PrefManager(this);
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
                    fetchDataFromServer(searchText.getText().toString());
                }
                else{
                    Toast.makeText(DashboardActivity.this, "Please provide some input", Toast.LENGTH_SHORT).show();
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

        findViewById(R.id.LogoutButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                prefManager.set(mIsLoggedIn, false);
                Toast.makeText(DashboardActivity.this, "Signing out...", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(DashboardActivity.this,LoginActivity.class));
            }
        });
    }
    //endregion

    //region perform mvvm server fetch
    private void fetchDataFromServer(String queryString){
        progressBar.setVisibility(View.VISIBLE);
        contentViewModel.getData(queryString);
        contentViewModel.getContent().observe(this, new Observer<Content>() {
            @Override
            public void onChanged(Content contents) {
                if (contents != null) {
                    content = contents;
                    loadListView();
                    contentAdapter.notifyDataSetChanged();
                    progressBar.setVisibility(View.GONE);
                    searchResultAppear.setVisibility(View.GONE);
                }
            }
        });
    }
    //endregion

    //region set recycler adapter
    private void loadListView(){
        contentAdapter = new ContentAdapter(content);
        contentRecycler.setLayoutManager(new LinearLayoutManager(this));
        contentRecycler.setAdapter(contentAdapter);
        contentAdapter.setOnItemClick(new ContentAdapter.onItemClick() {
            @Override
            public void itemClick(Content content) {
                startActivity(new Intent(DashboardActivity.this, ContentDetailsActivity.class).putExtra("content",content));
            }
        });
    }
    //endregion

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
    }

    //endregion
}