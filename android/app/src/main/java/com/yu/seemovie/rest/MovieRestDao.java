package com.yu.seemovie.rest;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

public class MovieRestDao {
    public static String rooturl = "http://192.168.111.148:8080/seemovieweb_war_exploded/";
    public static String operurl = rooturl + "oper/";
    public static String selecturl = operurl + "select";


    public static void getSelectMovie(int start, int count, Callback.CommonCallback<String> callback) {
        RequestParams params = new RequestParams(selecturl);
        params.addQueryStringParameter("start", start);
        params.addQueryStringParameter("count", count);
        x.http().get(params, callback);
    }

    public static void getLastestMovie(int count, Callback.CommonCallback<String> callback) {
        RequestParams params = new RequestParams(selecturl);
        params.addQueryStringParameter("lastest", count);
        x.http().get(params, callback);
    }

    public static void getMovieById(int id, Callback.CommonCallback<String> callback) {
        RequestParams params = new RequestParams(selecturl);
        params.addQueryStringParameter("id", id);
        x.http().get(params, callback);
    }


}