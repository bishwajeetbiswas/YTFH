package com.biswas.ytfh.network.response;

import com.biswas.ytfh.network.response.models.Category;
import com.biswas.ytfh.network.response.models.Ranking;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by bishwajeetbiswas on 02/10/17.
 */

public class YtfhResponse {
    @SerializedName("categories")
    public ArrayList<Category> mCategories = new ArrayList<>();
    @SerializedName("rankings")
    public ArrayList<Ranking> mRankings = new ArrayList<>();
}
