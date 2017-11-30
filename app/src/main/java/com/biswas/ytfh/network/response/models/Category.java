package com.biswas.ytfh.network.response.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by bishwajeetbiswas on 29/11/17.
 */

public class Category {
    @SerializedName("id")
    public int mId;

    @SerializedName("name")
    public String mName;

    @SerializedName("products")
    public ArrayList<Product> mProducts = new ArrayList<>();

    @SerializedName("child_categories")
    public ArrayList<Integer> mChildCategories = new ArrayList<>();
}
