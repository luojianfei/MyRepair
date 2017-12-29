package com.repair.proj.net;


import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by jniu on 2017/5/17.
 */

public interface HttpService {

    @POST("{path}")
//    @FormUrlEncoded
    Call<String> getData(@Path("path") String path,@Body RequestBody json);
}
