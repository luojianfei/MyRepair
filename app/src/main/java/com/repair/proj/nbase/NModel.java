package com.repair.proj.nbase;

/**
 * Created by HX·罗 on 2017/11/14.
 */

public class NModel {

    public static String createUrlString(String url){
        return String.format("%s%s",ModelConfig.BASE_SERVER_ADDRESS,url) ;
    }

}
