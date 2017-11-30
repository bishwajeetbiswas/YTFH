package com.biswas.ytfh.network.response.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by bishwajeetbiswas on 29/11/17.
 */

public class Tax {
    @SerializedName("name")
    public String mName;

    @SerializedName("value")
    public double mValue;

}
