package service;

import bean.Movie;
import dao.MovieDao;
import helper.DBHelper;

import java.io.IOException;
import java.util.List;

public class MovieService {

    MovieDao movieDao;

    public MovieService() {
        try {
            movieDao = DBHelper.getSqlSession().getMapper(MovieDao.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean insert(Movie movie) {
        return movieDao.insert(movie) > 0;
    }

    public boolean delete(int id) {
        return movieDao.deleteByPrimaryKey(id) > 0;
    }

    public int update(Movie movie) {
        return movieDao.updateByPrimaryKey(movie);
    }

    public Movie selectById(int id) {
        return movieDao.selectByPrimaryKey(id);
    }

    public List<Movie> selectSection(int start, int count) {
        return movieDao.selectSection(start, count);
    }

    public List<Movie> selectAll() {
        return movieDao.selectAll();
    }

    public List<Movie> selectLastest(int count) {
        return movieDao.selectLastest(count);
    }

    public List<Movie> selectByName(String name) {
        return movieDao.selectByName(name);
    }
}
