package com.biswas.ytfh.network.response.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by bishwajeetbiswas on 29/11/17.
 */

public class Variant {
    @SerializedName("id")
    public int mId;

    @SerializedName("color")
    public String mColor;

    @SerializedName("size")
    public int mSize;

    @SerializedName("price")
    public long mPrice;
}
