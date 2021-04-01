package model;

import bean.Movie;
import bean.MovieDao;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;


/**
 * @author hhyygg2009 on 2020/7/5.
 * @version 1.0
 */
public class MovieDAO {

    MovieDao movieDao;

    public MovieDAO() {
        SqlSessionFactory sqlSessionFactory = null;
        try {
            sqlSessionFactory = getSqlSessionFactory();
        } catch (IOException e) {
            e.printStackTrace();
        }
        SqlSession sqlSession = sqlSessionFactory.openSession();
        movieDao = sqlSession.getMapper(MovieDao.class);


    }

    public SqlSessionFactory getSqlSessionFactory() throws IOException {
        String resource = "mybatis-config.xml";
//        System.out.println(Resources.getResourceAsFile(".").getAbsolutePath());

        InputStream inputStream = Resources.getResourceAsStream(resource);
        return new SqlSessionFactoryBuilder().build(inputStream);
    }

    public boolean insert(Movie movie) {
        return movieDao.insert(movie) > 0;
    }

    public boolean delete(int id) {
        return movieDao.deleteByPrimaryKey(id) > 0;
    }

    public int update (Movie movie){ return movieDao.updateByPrimaryKey(movie);}

    public Movie selectById(int id){return movieDao.selectByPrimaryKey(id);}
    
    public List<Movie> selectSection(int start,int count){return movieDao.selectSection(start,count);}

    public List<Movie> selectAll(){return movieDao.selectAll();}

    public List<Movie> selectLastest(int count){return movieDao.selectLastest(count);}

    public List<Movie> selectByName(String name) {return movieDao.selectByName(name);    }
}
