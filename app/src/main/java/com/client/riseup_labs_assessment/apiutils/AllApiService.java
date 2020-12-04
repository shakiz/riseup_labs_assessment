package com.client.riseup_labs_assessment.apiutils;

import com.client.riseup_labs_assessment.models.contents.Content;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.Url;

public interface AllApiService {
    //Call for trending repositories
    @GET("shows")
    Call<Content> getContent(@Query("q") String q);
}
