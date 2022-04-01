package dao;

import bean.Movie;

import java.util.List;

public interface MovieDao {
    int deleteByPrimaryKey(Integer id);

    int insert(Movie record);

    int insertSelective(Movie record);

    Movie selectByPrimaryKey(Integer id);

    List<Movie> selectAll();

    int updateByPrimaryKeySelective(Movie record);

    int updateByPrimaryKey(Movie record);

    List<Movie> selectSection(int start, int count);

    List<Movie> selectLastest(int count);

    List<Movie> selectByName(String name);
}