
package com.biswas.ytfh.network.api;

import com.biswas.ytfh.network.response.YtfhResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface YtfhApi {
    @GET("json")
    Observable<YtfhResponse> getYtfhResponse();
}
