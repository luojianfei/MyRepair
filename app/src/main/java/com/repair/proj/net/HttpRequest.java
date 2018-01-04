package com.repair.proj.net;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import com.google.gson.Gson;
import com.repair.proj.nbase.ModelConfig;
import com.repair.proj.nbase.NModel;
import com.repair.proj.utils.MD5;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.TimeUnit;

import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by jniu on 2017/6/20.
 */

public class HttpRequest<T extends BaseResponse> {

    public static final String TAG = HttpRequest.class.getName();

//    public static final String BASE_URL = "http://139.224.70.219:85/";
    public static final String BASE_URL = ModelConfig.BASE_SERVER_ADDRESS;
    public static final String NETWORK_KEY = "1e56c95504a9a846e4c7043704a20f25";

    private static HttpService httpService;

    private static int readTimeout = 5;
    private static int writeTimeout = 10;

    private Context context;

    private TreeMap<String,String> params;

    private OnNetworkListener<T> listener;

    public static HttpService getHttpService() {
        if (httpService==null){
            httpService = initRetrofit();
        }
        return httpService;
    }

    private static HttpService initRetrofit(){
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(readTimeout, TimeUnit.SECONDS)
                .writeTimeout(writeTimeout, TimeUnit.SECONDS)
                .readTimeout(readTimeout, TimeUnit.SECONDS)
//                .cookieJar(new CookieJar() {
//                    private final HashMap<HttpUrl, List<Cookie>> cookieStore = new HashMap<>();
//
//                    @Override
//                    public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {
//                        cookieStore.put(url, cookies);
//                    }
//
//                    @Override
//                    public List<Cookie> loadForRequest(HttpUrl url) {
//                        List<Cookie> cookies = cookieStore.get(url);
//                        return cookies != null ? cookies : new ArrayList<Cookie>();
//                    }
//                })
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .client(client)
                .build();
        return retrofit.create(HttpService.class);
    }

    public HttpRequest setTimeout(int readTimeout,int writeTimeout){
        this.readTimeout = readTimeout;
        this.writeTimeout = writeTimeout;
        return this;
    }

    public HttpRequest with(Context context){
        this.context = context;
        return this;
    }

    public HttpRequest addParam(String key,String values){
        if (params == null) {
            params = new TreeMap<>();
        }
        params.put(key,values);
        return this;
    }

    public HttpRequest addParams(TreeMap<String,String> params){
        if (this.params == null) {
            this.params = new TreeMap<>();
        }
        this.params.putAll(params);
        return this;
    }

    public HttpRequest setListener(OnNetworkListener listener){
        this.listener = listener;
        return this;
    }

    public HttpRequest start(final String apiCode,final T t){
        if (context == null) {
            Log.e(TAG, "context不能为空");
            return this;
        }
        if (params == null) {
            Log.e(TAG,"请添加请求参数");
            return this;
        }
        if (listener == null) {
            Log.e(TAG,"请添加网络结果监听 Listener");
            return this;
        }
        if (checkNetworkStatus()) {
            RequestBody body=RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),postJson(params));
            getHttpService()
                    .getData(apiCode,body)
                    .enqueue(new Callback<String>() {
                        @Override
                        public void onResponse(Call<String> call, Response<String> response) {
                            t.parseJson(response.body());
                            if ("200".equals(t.code)) {
                                listener.onSuccess(t);
                            }else{
                                listener.onFail(t.msg);
                            }
                        }

                        @Override
                        public void onFailure(Call<String> call, Throwable t) {
                            listener.onFail(t.getMessage());
                        }
                    });
        }else{
            listener.onFail("当前无网络连接，请检查网络后重试");
        }


        return this;
    }

    /**
     * 生成请求token
     * @param params
     * @return
     */
    public String getPostToken1(TreeMap<String,String> params){
        StringBuilder stringBuilder = new StringBuilder();
        Iterator<Map.Entry<String, String>> iterator = params.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String,String> entry = iterator.next();
            stringBuilder.append(entry.getValue());
        }
        return MD5.Md5(stringBuilder.append(NETWORK_KEY).toString());
    }

    /**
     * 请求参数封装成json串
     * @param params
     * @return
     */
    public String postJson(TreeMap<String,String> params){
//        String token = getPostToken(params);
//        params.put("token",token);
        String json = new Gson().toJson(params);
        return json;
    }

    /**
     * 检查网络状态
     * @return
     */
    public boolean checkNetworkStatus(){
        ConnectivityManager connMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo networkInfo = connMgr.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        Boolean isWifiConn = networkInfo.isConnected();

        networkInfo = connMgr.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        Boolean isMobileConn = networkInfo.isConnected();

        return isMobileConn || isWifiConn;
    }

    public interface OnNetworkListener<T extends BaseResponse>{
        void onSuccess(T response);
        void onFail(String message);
    }
}
