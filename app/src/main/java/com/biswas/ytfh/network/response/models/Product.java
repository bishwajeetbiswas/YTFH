package com.biswas.ytfh.network.response.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Product {
    @SerializedName("id")
    public int mId;

    @SerializedName("view_count")
    public String mViewCount;

    @SerializedName("shares")
    public long mShares;

    @SerializedName("name")
    public String mName;

    @SerializedName("date_added")
    public String mDateAdded;

    @SerializedName("variants")
    public ArrayList<Variant> mVariants = new ArrayList<>();

    @SerializedName("tax")
    public Tax mTax;
}
