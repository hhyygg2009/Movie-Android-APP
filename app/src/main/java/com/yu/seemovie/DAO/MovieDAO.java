package com.yu.seemovie.DAO;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.yu.seemovie.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

public class MovieDAO {

    SQLiteDatabase db;

    Cursor cursor;

    public MovieDAO(Activity activity) {
        initDataModel(activity);
//        loadMoviesViaDB();
//        loadMoviesViaStr();


    }

    String dbGetStr(String columnName) {
        return cursor.getString(cursor.getColumnIndex(columnName));
    }

    private void initDataModel(Activity activity) {
        db = new BaseDAO(activity).getWritableDatabase();

    }

    private List loadMoviesViaStr() {

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

    public List loadMoviesViaDB() {
        String sql2 = "select * from movie";
        cursor = db.rawQuery(sql2, null);
        return getRecords(cursor);
    }

    public List getSectionMovieViaSql(int a, int b) {
        String sql2 = "select * from movie limit ?,?";
        cursor = db.rawQuery(sql2, new String[]{String.valueOf(a), String.valueOf(b)});
        return getRecords(cursor);
    }

    public List getLastestMovies(int count) {
        String sql2 = "select * from movie order by id desc limit " + count;
        cursor = db.rawQuery(sql2, null);
        return getRecords(cursor);
    }

    public Map getMovieById(int id) {
        String sql2 = "select * from movie where title like '%?%'";
        cursor = db.rawQuery(sql2, new String[]{String.valueOf(id)});

        return (Map<String, Object>) getRecords(cursor);

    }

    public Map getMovieByName(String name) {
        String sql2 = "select * from movie where  title=? ";
        cursor = db.rawQuery(sql2, new String[]{name});
        return (Map<String, Object>) getRecords(cursor);

    }

    private List<Map<String, Object>> getRecords(Cursor cursor) {
        ArrayList<Map<String, Object>> movies = new ArrayList<Map<String, Object>>();
        while (cursor.moveToNext()) {

            Map<String, Object> map = new HashMap<>();
            map.put("text", dbGetStr("title"));
            map.put("img", Integer.valueOf(dbGetStr("custompicpos")));
            map.put("subtitle", dbGetStr("title_sub"));
            map.put("story", dbGetStr("story"));
            movies.add(map);

        }

        int count = cursor.getCount();
        Log.i("db", "read: " + count);

        return movies;

    }
}
