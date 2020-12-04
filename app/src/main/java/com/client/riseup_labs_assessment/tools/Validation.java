package com.client.riseup_labs_assessment.tools;

import android.content.Context;
import android.text.TextUtils;

public class Validation {
    private Context context;

    public Validation(Context context) {
        this.context = context;
    }

    public boolean validateData(String data){
        boolean isValid = false;
        if (!TextUtils.isEmpty(data) && data.length() > 0){
            isValid = true;
        }
        return isValid;
    }
}
