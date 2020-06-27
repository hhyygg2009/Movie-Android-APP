package com.yu.seemovie.DAO;

import com.yu.seemovie.R;

import java.util.ArrayList;
import java.util.List;

public class staticdata {
    public static List<Integer> banners;

    public staticdata() {
        initBanner();
    }

    public static List<Integer> getBanners() {
        return banners;
    }

    public void initBanner() {

        banners = new ArrayList<Integer>();
        banners.add(R.drawable.banner__1_);
        banners.add(R.drawable.banner__2_);
        banners.add(R.drawable.banner__3_);
//        Log.i(TAG, "initData: "+moives.size());
    }


}
