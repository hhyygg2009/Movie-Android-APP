package com.yu.seemovie.DAO;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

public class MovieDAO {

    static String operurl = "http://192.168.1.192:8080/seemovieweb_war_exploded/oper/";
    static String selecturl = operurl + "select";

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

//    public  void getSelectMovie(int start, int count,IMovieDataLoad iMovieDataLoad){
//        getSelectMovie(start,count,new MovieDataCommonCallback(iMovieDataLoad));
//    }

//    public List<Movie> movies;
//    RecyclerView.Adapter adapter;

//    public MovieDAO(){
//        movies=new Vector<Movie>();
////        this.adapter = adapter;
//    }


//    public void MovieLoadLastest(int count){
//
//        MovieDAO.getLastestMovie(count, new Callback.CommonCallback<String>() {
//            @Override
//            public void onSuccess(String result) {
//                movies= JSON.parseArray(result.toString(), Movie.class);
//            }
//
//            @Override
//            public void onError(Throwable ex, boolean isOnCallback) {
//                //Toast.makeText(x.app(), ex.getMessage(), Toast.LENGTH_LONG).show();
//                if (ex instanceof HttpException) { // 网络错误
//                    HttpException httpEx = (HttpException) ex;
//                    int responseCode = httpEx.getCode();
//                    String responseMsg = httpEx.getMessage();
//                    String errorResult = httpEx.getResult();
//                    // ...
//                } else { // 其他错误
//                    // ...
//                }
//                Toast.makeText(x.app(), ex.getMessage(), Toast.LENGTH_LONG).show();
//            }
//
//            @Override
//            public void onCancelled(CancelledException cex) {
//                Toast.makeText(x.app(), "cancelled", Toast.LENGTH_LONG).show();
//            }
//
//            @Override
//            public void onFinished() {
//                adapter.notifyDataSetChanged();
//
//            }
//        });
//
//    }
//
//    public void MovieLoadById(int id, final IMovieDataLoad movieRefresh){
//
//        MovieDAO.getMovieById(id, new Callback.CommonCallback<String>() {
//            @Override
//            public void onSuccess(String result) {
//                movies= JSON.parseArray(result.toString(), Movie.class);
//            }
//
//            @Override
//            public void onError(Throwable ex, boolean isOnCallback) {
//                //Toast.makeText(x.app(), ex.getMessage(), Toast.LENGTH_LONG).show();
//                if (ex instanceof HttpException) { // 网络错误
//                    HttpException httpEx = (HttpException) ex;
//                    int responseCode = httpEx.getCode();
//                    String responseMsg = httpEx.getMessage();
//                    String errorResult = httpEx.getResult();
//                    // ...
//                } else { // 其他错误
//                    // ...
//                }
//                Toast.makeText(x.app(), ex.getMessage(), Toast.LENGTH_LONG).show();
//            }
//
//            @Override
//            public void onCancelled(CancelledException cex) {
//                Toast.makeText(x.app(), "cancelled", Toast.LENGTH_LONG).show();
//            }
//
//            @Override
//            public void onFinished() {
//                movieRefresh.RefreshMovieData();
//
//            }
//        });
//
//    }
//
//
//    public void MovieLoadSelected(int start,int count){
//
//        MovieDAO.getSelectMovie(start,count, new Callback.CommonCallback<String>() {
//            @Override
//            public void onSuccess(String result) {
//                movies= JSON.parseArray(result.toString(), Movie.class);
//            }
//
//            @Override
//            public void onError(Throwable ex, boolean isOnCallback) {
//                //Toast.makeText(x.app(), ex.getMessage(), Toast.LENGTH_LONG).show();
//                if (ex instanceof HttpException) { // 网络错误
//                    HttpException httpEx = (HttpException) ex;
//                    int responseCode = httpEx.getCode();
//                    String responseMsg = httpEx.getMessage();
//                    String errorResult = httpEx.getResult();
//                    // ...
//                } else { // 其他错误
//                    // ...
//                }
//                Toast.makeText(x.app(), ex.getMessage(), Toast.LENGTH_LONG).show();
//            }
//
//            @Override
//            public void onCancelled(CancelledException cex) {
//                Toast.makeText(x.app(), "cancelled", Toast.LENGTH_LONG).show();
//            }
//
//            @Override
//            public void onFinished() {
//                adapter.notifyDataSetChanged();
//
//            }
//        });
//
//    }
//
//    public void MovieLoadId(int id){
//
//        MovieDAO.getMovieById(id, new Callback.CommonCallback<String>() {
//            @Override
//            public void onSuccess(String result) {
//                movies= JSON.parseArray(result.toString(), Movie.class);
//            }
//
//            @Override
//            public void onError(Throwable ex, boolean isOnCallback) {
//                //Toast.makeText(x.app(), ex.getMessage(), Toast.LENGTH_LONG).show();
//                if (ex instanceof HttpException) { // 网络错误
//                    HttpException httpEx = (HttpException) ex;
//                    int responseCode = httpEx.getCode();
//                    String responseMsg = httpEx.getMessage();
//                    String errorResult = httpEx.getResult();
//                    // ...
//                } else { // 其他错误
//                    // ...
//                }
//                Toast.makeText(x.app(), ex.getMessage(), Toast.LENGTH_LONG).show();
//            }
//
//            @Override
//            public void onCancelled(CancelledException cex) {
//                Toast.makeText(x.app(), "cancelled", Toast.LENGTH_LONG).show();
//            }
//
//            @Override
//            public void onFinished() {
//                adapter.notifyDataSetChanged();
//
//            }
//        });
//
//    }

}