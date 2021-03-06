package com.client.riseup_labs_assessment.viewmodels;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.client.riseup_labs_assessment.models.contents.Content;
import com.client.riseup_labs_assessment.repositories.ContentRepository;

public class ContentViewModel extends AndroidViewModel {
    private ContentRepository contentRepository;
    private MutableLiveData<Content> content;

    public ContentViewModel(@NonNull Application application) {
        super(application);
    }

    public void getData(String queryString){
        contentRepository = ContentRepository.getInstance();
        content = contentRepository.getContents(queryString);
    }

    public MutableLiveData<Content> getContent() {
        return content;
    }
}
