package com.yu.seemovie.dao;


import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MovieDAODB {

    SQLiteDatabase db;


    public MovieDAODB(Context context) {
        initDataModel(context);
//        loadMoviesViaDB();
//        loadMoviesViaStr();


    }

    private void initDataModel(Context context) {
        db = DbHelper.getInstance(context).getWritableDatabase();

    }

    private String dbGetStr(Cursor cursor, String columnName) {
        return cursor.getString(cursor.getColumnIndex(columnName));
    }

    private List<Map<String, Object>> getRecords(Cursor cursor) {
        ArrayList<Map<String, Object>> movies = new ArrayList<Map<String, Object>>();
        while (cursor.moveToNext()) {

            Map<String, Object> map = new HashMap<>();
            map.put("id", Integer.valueOf(dbGetStr(cursor, "Id")));
            map.put("text", dbGetStr(cursor, "title"));
            map.put("img", Integer.valueOf(dbGetStr(cursor, "custompicpos")));
            map.put("subtitle", dbGetStr(cursor, "title_sub"));
            map.put("story", dbGetStr(cursor, "story"));
            movies.add(map);

        }

        int count = cursor.getCount();
        Log.i("db", "read: " + count);

        return movies;

    }

    public List<Map<String, Object>> getAllMovies() {
        String sql2 = "select * from movie";
        Cursor cursor = db.rawQuery(sql2, null);
        return getRecords(cursor);
    }

    public List<Map<String, Object>> getSectionMovie(int start, int count) {
        String sql2 = "select * from movie limit ?,?";
        Cursor cursor = db.rawQuery(sql2, new String[]{String.valueOf(start), String.valueOf(count)});
        return getRecords(cursor);
    }

    public List<Map<String, Object>> getLastestMovies(int count) {
        String sql2 = "select * from movie order by id desc limit " + count;
        Cursor cursor = db.rawQuery(sql2, null);
        return getRecords(cursor);
    }

    public Map<String, Object> getMovieById(int id) {
        String sql2 = "select * from movie where  id=? ";
        Cursor cursor = db.rawQuery(sql2, new String[]{String.valueOf(id)});

        return getRecords(cursor).get(0);

    }

    public List<Map<String, Object>> getMovieByName(String name) {
        String sql2 = "select * from movie where title like '%?%'";
        Cursor cursor = db.rawQuery(sql2, new String[]{name});
        return getRecords(cursor);

    }


}
