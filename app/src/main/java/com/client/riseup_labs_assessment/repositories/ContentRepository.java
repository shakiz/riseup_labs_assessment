package com.client.riseup_labs_assessment.repositories;

import androidx.lifecycle.MutableLiveData;
import com.client.riseup_labs_assessment.apiutils.AllApiService;
import com.client.riseup_labs_assessment.models.contents.Content;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.client.riseup_labs_assessment.tools.Constants.BASE_URL;
import static com.client.riseup_labs_assessment.tools.UtilsManager.getClient;

public class ContentRepository {
    private static ContentRepository INSTANCE = null;
    private AllApiService apiService;

    public static ContentRepository getInstance() {
        if (INSTANCE == null){
            INSTANCE = new ContentRepository();
        }
        return INSTANCE;
    }

    public MutableLiveData<Content> getContents(String queryString){
        final MutableLiveData<Content> content = new MutableLiveData<>();
        apiService = getClient(BASE_URL).create(AllApiService.class);
        final Call<Content> call = apiService.getContent(queryString);
        call.enqueue(new Callback<Content>() {
            @Override
            public void onResponse(Call<Content> call, Response<Content> response) {
                if (response.isSuccessful()){
                    if (response.body() != null){
                        content.setValue(response.body());
                    }
                }
            }

            @Override
            public void onFailure(Call<Content> call, Throwable t) {
                content.setValue(null);
            }
        });
        return content;
    }
}
