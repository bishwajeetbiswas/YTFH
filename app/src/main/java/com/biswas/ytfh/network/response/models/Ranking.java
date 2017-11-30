package com.biswas.ytfh.network.response.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by bishwajeetbiswas on 29/11/17.
 */

public class Ranking {
    @SerializedName("ranking")
    public String mRanking;
    @SerializedName("products")
    public ArrayList<Product> mProducts = new ArrayList<>();
}

