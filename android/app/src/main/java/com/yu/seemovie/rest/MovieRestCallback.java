package com.yu.seemovie.rest;

import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.yu.seemovie.entity.Movie;

import org.xutils.common.Callback;
import org.xutils.ex.HttpException;
import org.xutils.x;

import java.util.List;
import java.util.Vector;

import lombok.Data;

@Data
public class MovieRestCallback implements Callback.CommonCallback<String> {
    public List<Movie> movies;
    private IMovieDataAdapter iMovieDataAdapter1;

//    private MovieDataManage(){
//
//    }

    public MovieRestCallback(IMovieDataAdapter iMovieDataAdapter) {
        this.iMovieDataAdapter1 = iMovieDataAdapter;
        movies = new Vector<Movie>();
    }

    @Override
    public void onSuccess(String result) {
        movies.addAll(JSON.parseArray(result.toString(), Movie.class));
    }

    @Override
    public void onError(Throwable ex, boolean isOnCallback) {
        //Toast.makeText(x.app(), ex.getMessage(), Toast.LENGTH_LONG).show();
        if (ex instanceof HttpException) { // 网络错误
            HttpException httpEx = (HttpException) ex;
            int responseCode = httpEx.getCode();
            String responseMsg = httpEx.getMessage();
            String errorResult = httpEx.getResult();
            // ...
        } else { // 其他错误
            // ...
        }
        Toast.makeText(x.app(), ex.getMessage(), Toast.LENGTH_LONG).show();
    }

    @Override
    public void onCancelled(Callback.CancelledException cex) {
        Toast.makeText(x.app(), "cancelled", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onFinished() {
        iMovieDataAdapter1.RefreshMovieData();

    }
}