package com.yu.seemovie.DAO;

import com.yu.seemovie.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

public class staticdata {


    public List<Integer> getBanners() {
        List<Integer> banners;
        banners = new ArrayList<Integer>();
        banners.add(R.drawable.banner__1_);
        banners.add(R.drawable.banner__2_);
        banners.add(R.drawable.banner__3_);
        return banners;
    }

    public List loadMoviesViaStr() {

        List movies = new Vector<Map<String, Object>>();
        Map<String, Object> map;
        map = new HashMap<>();
        map.put("text", "电影1");
        map.put("img", R.drawable.cover__1_);
        movies.add(map);

        map = new HashMap<>();
        map.put("text", "电影2");
        map.put("img", R.drawable.cover__2_);
        movies.add(map);

        map = new HashMap<>();
        map.put("text", "电影3");
        map.put("img", R.drawable.cover__3_);
        movies.add(map);

        map = new HashMap<>();
        map.put("text", "电影4");
        map.put("img", R.drawable.cover__4_);
        movies.add(map);

        map = new HashMap<>();
        map.put("text", "电影5");
        map.put("img", R.drawable.cover__5_);
        movies.add(map);

        map = null;
        return movies;
    }

}
